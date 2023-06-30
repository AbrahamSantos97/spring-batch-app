package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.inheritance;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

public class AttributesTesis implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonAlias("registro_digital")
    private String registroDigital;

    private String instancia;

    private String epoca;

    private String tesis;

    private String fuente;

    private String tipo;

    private String rubro;

     public String getRegistroDigital() {
        return registroDigital;
    }

    public void setRegistroDigital(String registroDigital) {
        this.registroDigital = registroDigital;
    }

    public String getInstancia() {
        return instancia;
    }

    public void setInstancia(String instancia) {
        this.instancia = instancia;
    }

    public String getEpoca() {
        return epoca;
    }

    public void setEpoca(String epoca) {
        this.epoca = epoca;
    }

    public String getTesis() {
        return tesis;
    }

    public void setTesis(String tesis) {
        this.tesis = tesis;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    
}
