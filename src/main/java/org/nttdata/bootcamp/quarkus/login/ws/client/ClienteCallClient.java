package org.nttdata.bootcamp.quarkus.login.ws.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.nttdata.bootcamp.quarkus.login.ws.vo.ClienteVO;

@Path("/cliente")
@RegisterRestClient(configKey = "cliente-api")
public interface ClienteCallClient {
    @GET
    @Path("/{id}")
    Uni<ClienteVO> buscarClientePorId(@PathParam("id") String id);

}
