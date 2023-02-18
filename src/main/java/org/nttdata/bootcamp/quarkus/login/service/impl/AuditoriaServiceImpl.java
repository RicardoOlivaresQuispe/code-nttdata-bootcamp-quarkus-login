package org.nttdata.bootcamp.quarkus.login.service.impl;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.nttdata.bootcamp.quarkus.login.entity.Auditoria;
import org.nttdata.bootcamp.quarkus.login.repository.AuditoriaRepository;
import org.nttdata.bootcamp.quarkus.login.service.AuditoriaService;

import java.util.List;

@ApplicationScoped
public class AuditoriaServiceImpl implements AuditoriaService {

    @Inject
    AuditoriaRepository auditoriaRepository;

    @Override
    public Uni<List<Auditoria>> listAuditoria() {
        return auditoriaRepository.listAll();
    }
}
