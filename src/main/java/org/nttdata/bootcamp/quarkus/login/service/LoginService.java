package org.nttdata.bootcamp.quarkus.login.service;

import io.smallrye.mutiny.Uni;
import org.nttdata.bootcamp.quarkus.login.entity.Login;
import org.nttdata.bootcamp.quarkus.login.ws.vo.RequestLoginUpdateVO;
import org.nttdata.bootcamp.quarkus.login.ws.vo.RequestLoginVO;

import java.net.UnknownHostException;
import java.util.List;

public interface LoginService {

    Uni<Login> registrarLogin(RequestLoginVO request) throws UnknownHostException;

    Uni<Login> actualizarLogin(RequestLoginUpdateVO requestLoginUpdateVO) throws UnknownHostException;

    Uni<Login> buscarLoginById(String id);

    Uni<List<Login>> findAll();

}
