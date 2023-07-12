package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models;

import java.io.Serializable;

public class RelacionesTesis implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idRelacionTesis;
    private Integer idSeccionTesis;
    private Integer idTesisRelacion;
    private Integer idSeccionDocumento;
    private Integer idRelacionSimple;
    private Integer idDocumentoReal;

    public RelacionesTesis(){
        
    }

    public RelacionesTesis(Integer idRelacionSimple){
        this.idRelacionSimple = idRelacionSimple;
    }

    public Integer getIdRelacionTesis() {
        return idRelacionTesis;
    }
    public void setIdRelacionTesis(Integer idRelacionTesis) {
        this.idRelacionTesis = idRelacionTesis;
    }
    public Integer getIdSeccionTesis() {
        return idSeccionTesis;
    }
    public void setIdSeccionTesis(Integer idSeccionTesis) {
        this.idSeccionTesis = idSeccionTesis;
    }
    public Integer getIdTesisRelacion() {
        return idTesisRelacion;
    }
    public void setIdTesisRelacion(Integer idTesisRelacion) {
        this.idTesisRelacion = idTesisRelacion;
    }
    public Integer getIdSeccionDocumento() {
        return idSeccionDocumento;
    }
    public void setIdSeccionDocumento(Integer idSeccionDocumento) {
        this.idSeccionDocumento = idSeccionDocumento;
    }
    public Integer getIdRelacionSimple() {
        return idRelacionSimple;
    }
    public void setIdRelacionSimple(Integer idRelacionSimple) {
        this.idRelacionSimple = idRelacionSimple;
    }
    public Integer getIdDocumentoReal() {
        return idDocumentoReal;
    }
    public void setIdDocumentoReal(Integer idDocumentoReal) {
        this.idDocumentoReal = idDocumentoReal;
    }

    
    
}
