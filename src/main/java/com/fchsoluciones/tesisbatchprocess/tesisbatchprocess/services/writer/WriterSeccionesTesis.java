package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.SeccionesTesisDTO;

@Component
public class WriterSeccionesTesis extends JdbcBatchItemWriter<SeccionesTesisDTO> {

    public WriterSeccionesTesis(@Qualifier("legalDataSource") DataSource dataSource){
        setDataSource(dataSource);
        setSql("INSERT INTO secciones_tesis (id_tesis,contenido) VALUES (:idTesis, :contenidoTesis) ");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<SeccionesTesisDTO>());
    }
    
}
