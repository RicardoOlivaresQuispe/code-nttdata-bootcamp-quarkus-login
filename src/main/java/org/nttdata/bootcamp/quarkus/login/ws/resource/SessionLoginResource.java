package org.nttdata.bootcamp.quarkus.login.ws.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.nttdata.bootcamp.quarkus.login.service.SessionLoginService;
import org.nttdata.bootcamp.quarkus.login.ws.vo.SessionVO;

@Path("/login-session/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SessionLoginResource {

    @Inject
    SessionLoginService sessionLoginService;

    @POST
    @Path("/inicio")
    public Uni<Response> inicioSession(SessionVO sessionVO) {
        return sessionLoginService.validarInicioSesion(sessionVO)
                .onItem()
                .transform(login -> Response.ok().entity(login).build());
    }
}
