package org.nttdata.bootcamp.quarkus.login.service;

import io.smallrye.mutiny.Uni;
import org.nttdata.bootcamp.quarkus.login.entity.Auditoria;

import java.util.List;

public interface AuditoriaService {

    Uni<List<Auditoria>> listAuditoria();
}
