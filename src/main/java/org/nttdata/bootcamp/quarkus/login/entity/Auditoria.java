package org.nttdata.bootcamp.quarkus.login.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@MongoEntity(collection = "auditoria")
public class Auditoria {
    private ObjectId id;
    private String usuario;
    private LocalDateTime fechaCreacion;
    /*
     * 1 - registro
     * 2 - actualizacion
     * */
    private Long tipoOperacion;
    private Boolean exito;

    public Auditoria(String usuario, LocalDateTime fechaCreacion, Long tipoOperacion, Boolean exito) {
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.tipoOperacion = tipoOperacion;
        this.exito = exito;
    }

    public Auditoria() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Long tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public Boolean getExito() {
        return exito;
    }

    public void setExito(Boolean exito) {
        this.exito = exito;
    }
}
