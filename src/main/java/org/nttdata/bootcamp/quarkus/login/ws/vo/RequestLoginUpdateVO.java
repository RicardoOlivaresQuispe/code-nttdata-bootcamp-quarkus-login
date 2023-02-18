package org.nttdata.bootcamp.quarkus.login.ws.vo;

import org.bson.types.ObjectId;

public class RequestLoginUpdateVO {
    private ObjectId idTrget;
    private String numberTarget;
    private String passwordSesion;
    private String pinTarjeta;

    public ObjectId getIdTrget() {
        return idTrget;
    }

    public void setIdTrget(ObjectId idTrget) {
        this.idTrget = idTrget;
    }

    public String getNumberTarget() {
        return numberTarget;
    }

    public void setNumberTarget(String numberTarget) {
        this.numberTarget = numberTarget;
    }

    public String getPasswordSesion() {
        return passwordSesion;
    }

    public void setPasswordSesion(String passwordSesion) {
        this.passwordSesion = passwordSesion;
    }

    public String getPinTarjeta() {
        return pinTarjeta;
    }

    public void setPinTarjeta(String pinTarjeta) {
        this.pinTarjeta = pinTarjeta;
    }
}
