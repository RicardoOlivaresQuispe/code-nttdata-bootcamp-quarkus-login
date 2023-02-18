package org.nttdata.bootcamp.quarkus.login.ws.vo;

public class ClienteVO {
    private Long id;
    private String estado;
    private String fechaNacimientoCreacion;
    private String nombreRazonSocial;
    private String numeroDocumento;
    private String tipoCliente;
    private String tipoDocumento;

    public ClienteVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaNacimientoCreacion() {
        return fechaNacimientoCreacion;
    }

    public void setFechaNacimientoCreacion(String fechaNacimientoCreacion) {
        this.fechaNacimientoCreacion = fechaNacimientoCreacion;
    }

    public String getNombreRazonSocial() {
        return nombreRazonSocial;
    }

    public void setNombreRazonSocial(String nombreRazonSocial) {
        this.nombreRazonSocial = nombreRazonSocial;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
