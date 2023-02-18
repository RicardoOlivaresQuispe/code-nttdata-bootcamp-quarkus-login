package org.nttdata.bootcamp.quarkus.login.entity;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "login")
public class Login {

    private ObjectId id;
    private String numTarjetaDebito;
    private String password;

    public Login() {
    }

    public Login(String numTarjetaDebito, String password) {
        this.numTarjetaDebito = numTarjetaDebito;
        this.password = password;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNumTarjetaDebito() {
        return numTarjetaDebito;
    }

    public void setNumTarjetaDebito(String numTarjetaDebito) {
        this.numTarjetaDebito = numTarjetaDebito;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
