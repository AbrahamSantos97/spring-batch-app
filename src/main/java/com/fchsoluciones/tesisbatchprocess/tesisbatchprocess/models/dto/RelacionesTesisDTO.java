package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class RelacionesTesisDTO {
    
    @JsonAlias("registro_digital")
    private String registroDigital;

    private List<RelacionDTO> relaciones;

    public String getRegistroDigital() {
        return registroDigital;
    }

    public void setRegistroDigital(String registroDigital) {
        this.registroDigital = registroDigital;
    }

    public List<RelacionDTO> getRelaciones() {
        return relaciones;
    }

    public void setRelaciones(List<RelacionDTO> relaciones) {
        this.relaciones = relaciones;
    }
    

    
}
