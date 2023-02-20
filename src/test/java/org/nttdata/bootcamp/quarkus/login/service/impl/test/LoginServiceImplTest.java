package org.nttdata.bootcamp.quarkus.login.service.impl.test;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.nttdata.bootcamp.quarkus.login.entity.Login;
import org.nttdata.bootcamp.quarkus.login.repository.AuditoriaRepository;
import org.nttdata.bootcamp.quarkus.login.repository.LoginRepository;
import org.nttdata.bootcamp.quarkus.login.service.impl.LoginServiceImpl;
import org.nttdata.bootcamp.quarkus.login.ws.client.ClienteCallClient;
import org.nttdata.bootcamp.quarkus.login.ws.client.CuentaBancariaCallClient;
import org.nttdata.bootcamp.quarkus.login.ws.client.TarjetaCallClient;
import org.nttdata.bootcamp.quarkus.login.ws.vo.ClienteVO;
import org.nttdata.bootcamp.quarkus.login.ws.vo.CuentaBancariaVO;
import org.nttdata.bootcamp.quarkus.login.ws.vo.RequestLoginVO;
import org.nttdata.bootcamp.quarkus.login.ws.vo.TarjetaVO;

import java.net.UnknownHostException;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;

public class LoginServiceImplTest {

    @InjectMocks
    LoginServiceImpl service;

    @Mock
    LoginRepository loginRepository;
    @Mock
    ClienteCallClient clienteCallClient;
    @Mock
    TarjetaCallClient tarjetaCallClient;
    @Mock
    CuentaBancariaCallClient cuentaBancariaCallClient;
    @Mock
    AuditoriaRepository auditoriaRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void registrarLoginTest() throws UnknownHostException {
        RequestLoginVO requestLoginVO = new RequestLoginVO();
        requestLoginVO.setCodValidacion("123");
        requestLoginVO.setFecVencimiento(LocalDate.now());
        requestLoginVO.setNumDocumento("1234567");
        requestLoginVO.setNumTrajeta("123456789741254");
        requestLoginVO.setTipoCliente("1");
        requestLoginVO.setPinTarjeta("123");
        TarjetaVO tarjetaVO = new TarjetaVO();
        tarjetaVO.setId(1L);
        tarjetaVO.setEstado("1");
        tarjetaVO.setNumeroTarjeta("123456789741254");
        tarjetaVO.setPin("123");
        tarjetaVO.setCodigoValidacion("123");
        tarjetaVO.setFechaVencimiento(LocalDate.now().toString());
        tarjetaVO.setTipotarjeta("1");
        CuentaBancariaVO cuentaBancariaVO = new CuentaBancariaVO();
        cuentaBancariaVO.setId(1L);
        cuentaBancariaVO.setClienteId(1L);
        Login login = new Login();
        ClienteVO clienteVO = new ClienteVO();
        clienteVO.setEstado("1");
        clienteVO.setTipoCliente("1");
        clienteVO.setNumeroDocumento("1234567");
        Uni<TarjetaVO> tarjetaVOUni = Uni.createFrom().item(tarjetaVO);
        Uni<Login> loginUni = Uni.createFrom().item(login);
        Uni<CuentaBancariaVO> cuentaBancariaVOUni = Uni.createFrom().item(cuentaBancariaVO);
        Uni<ClienteVO> clienteVOUni = Uni.createFrom().item(clienteVO);
        when(loginRepository.finLoginByNumeroTarjeta(anyString())).thenReturn(loginUni);
        when(tarjetaCallClient.buscarTarjetaPorNumTarjeta(anyString())).thenReturn(tarjetaVOUni);
        when(cuentaBancariaCallClient.buscarCuentaBancariaByIdtarjeta(anyString())).thenReturn(cuentaBancariaVOUni);
        service.registrarLogin(requestLoginVO);

    }
}
