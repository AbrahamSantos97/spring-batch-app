package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto;

import java.io.Serializable;

public class SeccionesTesisDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;

    private Integer idTesis;
    private String contenidoTesis;
    private String primeraSeccion;

    public SeccionesTesisDTO(Integer idTesis, String contenidoTesis, String primeraSeccion){
        this.idTesis = idTesis;
        this.contenidoTesis = contenidoTesis;
        this.primeraSeccion = primeraSeccion;
    }

    public Integer getIdTesis() {
        return idTesis;
    }
    public void setIdTesis(Integer idTesis) {
        this.idTesis = idTesis;
    }
    
    public String getContenidoTesis() {
        return contenidoTesis;
    }
    public void setContenidoTesis(String contenidoTesis) {
        this.contenidoTesis = contenidoTesis;
    }
    public String getPrimeraSeccion() {
        return primeraSeccion;
    }
    public void setPrimeraSeccion(String primeraSeccion) {
        this.primeraSeccion = primeraSeccion;
    }
 
}
