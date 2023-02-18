package org.nttdata.bootcamp.quarkus.login.ws.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.nttdata.bootcamp.quarkus.login.ws.vo.TarjetaVO;

@Path("tarjeta")
@RegisterRestClient(configKey = "tarjeta-api")
public interface TarjetaCallClient {

    @GET
    @Path("/numero-tarjeta")
    Uni<TarjetaVO> buscarTarjetaPorNumTarjeta(@QueryParam("numeroTarjeta") String numeroTarjeta);
}
