package org.nttdata.bootcamp.quarkus.login.ws.resource;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.nttdata.bootcamp.quarkus.login.entity.Auditoria;
import org.nttdata.bootcamp.quarkus.login.repository.AuditoriaRepository;
import org.nttdata.bootcamp.quarkus.login.service.AuditoriaService;

import java.util.List;

@Path("/auditoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuditoriaResource {

    @Inject
    AuditoriaService auditoriaService;

    @GET
    public Uni<List<Auditoria>> listarAuditoria() {
        return auditoriaService.listAuditoria();
    }
}
