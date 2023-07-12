package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesBot;

@Component
public class WriterRelacionesBotTesis extends JdbcBatchItemWriter<RelacionesBot>{

    public WriterRelacionesBotTesis(@Qualifier("legalDataSource") DataSource dataSource){
        setDataSource(dataSource);
        setSql("INSERT INTO relaciones_bot (registro_digital,nombre_documento,nombre_articulo) VALUES (:registroDigital, :nombreDocumento, :nombreArticulo) ");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<RelacionesBot>());
    }
    
}
