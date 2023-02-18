package org.nttdata.bootcamp.quarkus.login.service.impl;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.nttdata.bootcamp.quarkus.login.entity.Auditoria;
import org.nttdata.bootcamp.quarkus.login.entity.Login;
import org.nttdata.bootcamp.quarkus.login.repository.AuditoriaRepository;
import org.nttdata.bootcamp.quarkus.login.repository.LoginRepository;
import org.nttdata.bootcamp.quarkus.login.service.LoginService;
import org.nttdata.bootcamp.quarkus.login.util.ConstantesError;
import org.nttdata.bootcamp.quarkus.login.util.ConstantesPassword;
import org.nttdata.bootcamp.quarkus.login.ws.client.ClienteCallClient;
import org.nttdata.bootcamp.quarkus.login.ws.client.CuentaBancariaCallClient;
import org.nttdata.bootcamp.quarkus.login.ws.client.TarjetaCallClient;
import org.nttdata.bootcamp.quarkus.login.ws.vo.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class LoginServiceImpl implements LoginService {

    @Inject
    LoginRepository loginRepository;
    @RestClient
    ClienteCallClient clienteCallClient;
    @RestClient
    TarjetaCallClient tarjetaCallClient;
    @RestClient
    CuentaBancariaCallClient cuentaBancariaCallClient;
    @Inject
    AuditoriaRepository auditoriaRepository;

    @Override
    public Uni<Login> registrarLogin(RequestLoginVO requestLoginVO) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        return loginRepository.finLoginByNumeroTarjeta(requestLoginVO.getNumTrajeta()).onItem().transformToUni(Unchecked.function(login -> {
            if (Objects.isNull(login)) {
                return validateTarget(requestLoginVO)
                        .onItem()
                        .transformToUni(tarjetaVO -> findCuentaBancaria(String.valueOf(tarjetaVO.getId())))
                        .onItem()
                        .transformToUni(cuentaBancariaVO -> validateClient(requestLoginVO, String.valueOf(cuentaBancariaVO.getClienteId())))
                        .onItem()
                        .transformToUni(clienteVO ->
                                auditoriaRepository.persist(new Auditoria(ip.getHostAddress(), LocalDateTime.now(), 1L, true))
                                        .onItem()
                                        .transformToUni(auditoria -> loginRepository.persist(new Login(requestLoginVO.getNumTrajeta(), generatePassword()))));
            } else {
                throw new WebApplicationException(Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorMensaje(ConstantesError.ERROR_TARJETA_YA_REGISTRADA
                                .replace("{numTarjeta}", requestLoginVO.getNumTrajeta())))
                        .build());
            }
        }));
    }


    private String generatePassword() {
        int length = 6;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ConstantesPassword.CHARACTER_LIST.size());
            password.append(ConstantesPassword.CHARACTER_LIST.get(index));
        }
        return password.toString();
    }

    @Override
    public Uni<Login> actualizarLogin(RequestLoginUpdateVO requestLoginUpdateVO) throws UnknownHostException {
        InetAddress ip = InetAddress.getLocalHost();
        Uni<Login> objLogin = loginRepository.findById(requestLoginUpdateVO.getIdTrget());

        return objLogin
                .onItem()
                .transformToUni(login1 -> tarjetaCallClient.buscarTarjetaPorNumTarjeta(requestLoginUpdateVO.getNumberTarget()))
                .onItem()
                .transformToUni(Unchecked.function(tarjetaVO -> {
                    if (requestLoginUpdateVO.getPinTarjeta().equals(tarjetaVO.getPin())) {
                        return objLogin;
                    } else {
                        throw new WebApplicationException(Response
                                .status(Response.Status.BAD_REQUEST)
                                .entity(ConstantesError.ERROR_PIN_INVALIDO)
                                .build());
                    }
                })).onItem().transformToUni(login -> {
                    login.setPassword(requestLoginUpdateVO.getPasswordSesion());
                    login.setNumTarjetaDebito(requestLoginUpdateVO.getNumberTarget());
                    return auditoriaRepository.persist(new Auditoria(ip.getHostAddress(), LocalDateTime.now(), 2L, true))
                            .onItem().transformToUni(auditoria -> loginRepository.update(login));
                });
    }

    @Override
    public Uni<Login> buscarLoginById(String id) {
        return loginRepository.findById(new ObjectId(id));
    }

    @Override
    public Uni<List<Login>> findAll() {
        return loginRepository.listAll();
    }

    private Uni<TarjetaVO> validateTarget(RequestLoginVO requestLoginVO) {
        return tarjetaCallClient.buscarTarjetaPorNumTarjeta(requestLoginVO.getNumTrajeta())
                .map(Unchecked.function(tarjetaVO ->
                {
                    if (validateDataTarget(requestLoginVO, tarjetaVO)) {
                        return tarjetaVO;
                    } else {
                        throw new WebApplicationException(Response
                                .status(Response.Status.BAD_REQUEST)
                                .entity(ConstantesError.ERROR_DATOS_TARJETA)
                                .build());
                    }
                }));
    }

    private boolean validateDataTarget(RequestLoginVO request, TarjetaVO tarjetaVO) {

        return request.getNumTrajeta().equals(tarjetaVO.getNumeroTarjeta()) &&
                request.getFecVencimiento().isAfter(LocalDate.now()) &&
                request.getCodValidacion().equals(tarjetaVO.getCodigoValidacion()) &&
                request.getPinTarjeta().equals(tarjetaVO.getPin());
    }

    private Uni<ClienteVO> validateClient(RequestLoginVO request, String id) {

        return clienteCallClient.buscarClientePorId(id)
                .map(Unchecked.function(clienteVO -> {
                    if (validateDataCliente(request, clienteVO)) {
                        return clienteVO;
                    } else {
                        throw new WebApplicationException(Response
                                .status(Response.Status.BAD_REQUEST)
                                .entity(new ErrorMensaje(ConstantesError.ERROR_DATOS_CLIENTE))
                                .build());
                    }
                }));
    }

    private boolean validateDataCliente(RequestLoginVO request, ClienteVO clienteVO) {

        return request.getNumDocumento().equals(clienteVO.getNumeroDocumento()) &&
                request.getTipoCliente().equals(clienteVO.getTipoCliente());
    }

    private Uni<CuentaBancariaVO> findCuentaBancaria(String id) {
        return cuentaBancariaCallClient.buscarCuentaBancariaByIdtarjeta(id).map(
                Unchecked.function(cuentaBancariaVO -> {
                    if (Objects.isNull(cuentaBancariaVO)) {
                        throw new WebApplicationException(Response
                                .status(Response.Status.BAD_REQUEST)
                                .entity(new ErrorMensaje(ConstantesError.ERROR_CUENTA_BANCARIA))
                                .build());
                    } else {
                        return cuentaBancariaVO;
                    }
                })
        );
    }
}
