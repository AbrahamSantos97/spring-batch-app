package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.inheritance.AttributesTesis;

public class TesisDTO extends AttributesTesis {

    private List<String> contenido = new ArrayList<>();

    private List<String> precedentes = new ArrayList<>();

    private String publicacion;

    public List<String> getContenido() {
        return contenido;
    }

    public void setContenido(List<String> contenido) {
        this.contenido = contenido;
    }

    public List<String> getPrecedentes() {
        return precedentes;
    }

    public void setPrecedentes(List<String> precedentes) {
        this.precedentes = precedentes;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getAllContent(){
        return Stream.concat(getContenido().stream(), getPrecedentes().stream())
        .map(element -> element.concat("<br>"))
        .collect(Collectors.joining())
        .concat(getPublicacion());
    }

    
    @Override
    public String toString() {
        return "{rubro: "+getRubro()+"}";
    
    }


}
