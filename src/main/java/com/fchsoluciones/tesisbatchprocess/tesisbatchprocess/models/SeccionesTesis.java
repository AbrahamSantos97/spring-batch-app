package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models;

import java.io.Serializable;

public class SeccionesTesis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idSeccionTesis;

    private Integer idTesis;

    private String contenido;

    public Integer getIdSeccionTesis() {
        return idSeccionTesis;
    }

    public void setIdSeccionTesis(Integer idSeccionTesis) {
        this.idSeccionTesis = idSeccionTesis;
    }

    public Integer getIdTesis() {
        return idTesis;
    }

    public void setIdTesis(Integer idTesis) {
        this.idTesis = idTesis;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    

    
}
