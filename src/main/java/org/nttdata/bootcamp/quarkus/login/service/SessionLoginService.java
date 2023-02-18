package org.nttdata.bootcamp.quarkus.login.service;

import io.smallrye.mutiny.Uni;
import org.nttdata.bootcamp.quarkus.login.entity.Login;
import org.nttdata.bootcamp.quarkus.login.ws.vo.SessionVO;

public interface SessionLoginService {

    public Uni<Login> validarInicioSesion(SessionVO sessionVO);
}
