package org.nttdata.bootcamp.quarkus.login.ws.vo;

import java.time.LocalDate;

public class RequestLoginVO {

    private String numTrajeta;
    private LocalDate fecVencimiento;
    private String codValidacion;
    private String tipoCliente;
    private String numDocumento;
    private String pinTarjeta;

    public String getNumTrajeta() {
        return numTrajeta;
    }

    public void setNumTrajeta(String numTrajeta) {
        this.numTrajeta = numTrajeta;
    }

    public LocalDate getFecVencimiento() {
        return fecVencimiento;
    }

    public void setFecVencimiento(LocalDate fecVencimiento) {
        this.fecVencimiento = fecVencimiento;
    }

    public String getCodValidacion() {
        return codValidacion;
    }

    public void setCodValidacion(String codValidacion) {
        this.codValidacion = codValidacion;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getPinTarjeta() {
        return pinTarjeta;
    }

    public void setPinTarjeta(String pinTarjeta) {
        this.pinTarjeta = pinTarjeta;
    }
}
