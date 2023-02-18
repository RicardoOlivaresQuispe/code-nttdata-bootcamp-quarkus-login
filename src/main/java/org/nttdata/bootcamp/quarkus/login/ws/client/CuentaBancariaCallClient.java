package org.nttdata.bootcamp.quarkus.login.ws.client;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.nttdata.bootcamp.quarkus.login.ws.vo.CuentaBancariaVO;

@Path("/cuenta-bancaria")
@RegisterRestClient(configKey = "cuenta-bancaria-api")
public interface CuentaBancariaCallClient {
    @GET
    @Path("/tarjeta/{idTarjeta}")
    Uni<CuentaBancariaVO> buscarCuentaBancariaByIdtarjeta(@PathParam("idTarjeta") String idTarjeta);
}
