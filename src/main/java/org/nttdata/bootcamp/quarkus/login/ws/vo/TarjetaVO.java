package org.nttdata.bootcamp.quarkus.login.ws.vo;

public class TarjetaVO {

    private Long id;
    private String codigoValidacion;
    private String estado;
    private String fechaVencimiento;
    private String numeroTarjeta;
    private String pin;
    private String tipotarjeta;

    public TarjetaVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoValidacion() {
        return codigoValidacion;
    }

    public void setCodigoValidacion(String codigoValidacion) {
        this.codigoValidacion = codigoValidacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTipotarjeta() {
        return tipotarjeta;
    }

    public void setTipotarjeta(String tipotarjeta) {
        this.tipotarjeta = tipotarjeta;
    }

    @Override
    public String toString() {
        return "TarjetaVO{" +
                "id=" + id +
                ", codigoValidacion='" + codigoValidacion + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaVencimiento='" + fechaVencimiento + '\'' +
                ", numeroTarjeta='" + numeroTarjeta + '\'' +
                ", pin='" + pin + '\'' +
                ", tipotarjeta='" + tipotarjeta + '\'' +
                '}';
    }
}
