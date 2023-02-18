package org.nttdata.bootcamp.quarkus.login.repository;

import com.mongodb.client.model.Filters;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.nttdata.bootcamp.quarkus.login.entity.Login;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.nttdata.bootcamp.quarkus.login.util.ConstantesMongo;
import org.nttdata.bootcamp.quarkus.login.ws.vo.SessionVO;

import java.util.logging.Filter;

@ApplicationScoped
public class LoginRepository implements ReactivePanacheMongoRepository<Login> {

    public Uni<Login> finLoginByNumeroTarjeta(String numTarjeta) {
        return find(ConstantesMongo.LOGIN_NUM_TARJETA_DEBITO, numTarjeta).firstResult();
    }

    public Uni<Login> validarCuenta(SessionVO sessionVO) {
        Document document = new Document();
        document.append(ConstantesMongo.LOGIN_PASSWORD, sessionVO.getPassword());
        document.append(ConstantesMongo.LOGIN_NUM_TARJETA_DEBITO, sessionVO.getNumTarget());

        return find(document).firstResult();
    }

}
