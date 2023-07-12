package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesBot;

@Component
public class ReaderCreateRelations extends JdbcCursorItemReader<RelacionesBot> {

    public ReaderCreateRelations(@Qualifier("legalDataSource") DataSource dataSource){
        setDataSource(dataSource);
        setSql("SELECT id_relacion_bot AS idRelacionesBot, registro_digital AS registroDigital, nombre_documento AS nombreDocumento, nombre_articulo AS nombreArticulo "+
        "FROM relaciones_bot WHERE relaciones_bot.registro_relacion_creado = 0 ");
        BeanPropertyRowMapper<RelacionesBot> mapper = new BeanPropertyRowMapper<RelacionesBot>();
        mapper.setMappedClass(RelacionesBot.class);
        setRowMapper(mapper);
    }
    
}
