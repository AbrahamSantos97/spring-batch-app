package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models;

import java.io.Serializable;

public class RelacionesBot implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRelacionesBot;
    private String registroDigital;
    private String nombreDocumento;
    private String nombreArticulo;

    public RelacionesBot(){
        
    }

    public RelacionesBot(String registroDigital){
        this.registroDigital = registroDigital;
    }

    public RelacionesBot(String nombreDocumento, String nombreArticulo){
        this.nombreArticulo = nombreArticulo;
        this.nombreDocumento = nombreDocumento;
    }
    
    public Integer getIdRelacionesBot() {
        return idRelacionesBot;
    }
    public void setIdRelacionesBot(Integer idRelacionesBot) {
        this.idRelacionesBot = idRelacionesBot;
    }
    public String getRegistroDigital() {
        return registroDigital;
    }
    public void setRegistroDigital(String registroDigital) {
        this.registroDigital = registroDigital;
    }
    public String getNombreDocumento() {
        return nombreDocumento;
    }
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    public String getNombreArticulo() {
        return nombreArticulo;
    }
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    

}
