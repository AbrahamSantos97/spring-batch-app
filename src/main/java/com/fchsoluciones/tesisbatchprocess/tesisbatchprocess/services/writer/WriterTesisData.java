package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.dto.TesisDTO;

@Component
public class WriterTesisData extends JdbcBatchItemWriter<TesisDTO> {

    public WriterTesisData(@Qualifier("legalDataSource") DataSource dataSource) {
        setDataSource(dataSource);
        setSql("INSERT INTO tesis_pruebas (registro_digital, instancia, epoca, ntesis, fuente, tipo, rubro) "+
        "VALUES (:registroDigital, :instancia, :epoca, :tesis, :fuente, :tipo, :rubro) ");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<TesisDTO>());
    }

}
