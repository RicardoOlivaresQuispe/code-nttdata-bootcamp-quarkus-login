package org.nttdata.bootcamp.quarkus.login.service.impl;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.nttdata.bootcamp.quarkus.login.entity.Login;
import org.nttdata.bootcamp.quarkus.login.repository.LoginRepository;
import org.nttdata.bootcamp.quarkus.login.service.SessionLoginService;
import org.nttdata.bootcamp.quarkus.login.ws.vo.SessionVO;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ApplicationScoped
public class SessionLoginServiceImpl implements SessionLoginService {

    @Inject
    LoginRepository loginRepository;

    @Override
    public Uni<Login> validarInicioSesion(SessionVO sessionVO) {

        return loginRepository.validarCuenta(sessionVO).map(Unchecked.function(login -> {
            if (Objects.isNull(login)) {
                Map<String, String> mensaje = new HashMap<>();
                mensaje.put("errorMensaje", "NO EXISTE CUENTA PARA EL NUMERO DE TARJETA " + sessionVO.getNumTarget());
                throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(mensaje).build());
            }
            return login;
        }));
    }
}
