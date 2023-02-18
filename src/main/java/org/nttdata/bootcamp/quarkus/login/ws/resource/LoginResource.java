package org.nttdata.bootcamp.quarkus.login.ws.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nttdata.bootcamp.quarkus.login.entity.Login;
import org.nttdata.bootcamp.quarkus.login.service.LoginService;
import org.nttdata.bootcamp.quarkus.login.ws.vo.RequestLoginUpdateVO;
import org.nttdata.bootcamp.quarkus.login.ws.vo.RequestLoginVO;

import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    LoginService loginService;

    @POST
    @Path("/save")
    public Uni<Response> registrarLogin(RequestLoginVO request) throws UnknownHostException {

        return loginService.registrarLogin(request)
                .onItem()
                .transform(inserted -> Response.created(URI.create("/save/"))
                        .entity(inserted)
                        .build());
    }

    @PUT
    @Path("/update")
    public Uni<Response> actualizarLogin(RequestLoginUpdateVO requestLoginUpdateVO) throws UnknownHostException {

        return loginService.actualizarLogin(requestLoginUpdateVO).map(x -> Response.ok().entity(x).build());
    }

    @GET
    @Path("/buscarId/{id}")
    public Uni<Login> actualizarLogin(@PathParam("id") String id) {
        return loginService.buscarLoginById(id);
    }

    @GET
    public Uni<List<Login>> listarLogin() {
        return loginService.findAll();
    }

}
