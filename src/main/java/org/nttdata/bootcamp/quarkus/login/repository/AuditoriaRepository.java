package org.nttdata.bootcamp.quarkus.login.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.nttdata.bootcamp.quarkus.login.entity.Auditoria;

@ApplicationScoped
public class AuditoriaRepository implements ReactivePanacheMongoRepository<Auditoria> {
}
