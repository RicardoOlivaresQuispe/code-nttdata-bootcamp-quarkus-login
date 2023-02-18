package org.nttdata.bootcamp.quarkus.login.ws.vo;

public class ErrorMensaje {

    private String mensajeError;

    public ErrorMensaje(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
