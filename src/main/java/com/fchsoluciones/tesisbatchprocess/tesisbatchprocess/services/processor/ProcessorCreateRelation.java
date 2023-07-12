package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.processor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesBot;
import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesTesis;

@Component
public class ProcessorCreateRelation implements ItemProcessor<RelacionesBot,RelacionesTesis> {

    private final static String QRY_GET_TESIS = "SELECT id_tesis FROM tesis_bot WHERE tesis_bot.registro_digital = :rd LIMIT 1 ";
    private final static String QRY_GET_SECCION_TESIS = "SELECT id_seccion_tesis FROM secciones_tesis WHERE secciones_tesis.id_tesis = :id AND secciones_tesis.tipo_contenido = 'principal' ";
    private final static String QRY_GET_DOCUMENTO_REAL = "SELECT id_documento FROM documentos WHERE documentos.nombre = :nom AND documentos.estatus = 1 AND documentos.fecha_fin IS NULL ";
    private final static String QRY_GET_NIVEL_SUPERIOR = "SELECT id_seccion FROM secciones WHERE secciones.titulo = :nom AND secciones.id_documento = :id ";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public ProcessorCreateRelation(NamedParameterJdbcTemplate namedParameterJdbcTemplate){
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public RelacionesTesis process(RelacionesBot item) throws Exception {
        RelacionesTesis current = new RelacionesTesis(item.getIdRelacionesBot());
        try {
        Integer idTesis = namedParameterJdbcTemplate.queryForObject(QRY_GET_TESIS, 
        new MapSqlParameterSource().addValue("rd", item.getRegistroDigital()),Integer.class);
        current.setIdTesisRelacion(idTesis);
        if(Objects.nonNull(idTesis)){
            Integer idSeccionTesis = namedParameterJdbcTemplate.queryForObject(QRY_GET_SECCION_TESIS, 
            new MapSqlParameterSource(Collections.singletonMap("id", idTesis)),Integer.class);
            current.setIdSeccionTesis(idSeccionTesis);         
            Integer idDocumentoReal = namedParameterJdbcTemplate.queryForObject(QRY_GET_DOCUMENTO_REAL, 
            Collections.singletonMap("nom", item.getNombreDocumento()),Integer.class);
            current.setIdDocumentoReal(idDocumentoReal);
            if(Objects.nonNull(idDocumentoReal)){
                Map<String,Object> arguments = new HashMap<>();
                arguments.put("nom", item.getNombreArticulo().concat("."));
                arguments.put("id", idDocumentoReal);
                Integer idSeccion = namedParameterJdbcTemplate.queryForObject(QRY_GET_NIVEL_SUPERIOR, 
                arguments, Integer.class);
                current.setIdSeccionDocumento(idSeccion);
            }
        }   
        }catch(EmptyResultDataAccessException ex){
            System.out.println("error");
        }
       return current;
    }
    
}
