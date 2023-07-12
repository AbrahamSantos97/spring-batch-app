package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.RelacionesTesis;

@Component
public class WriterRelacionesTesis extends JdbcBatchItemWriter<RelacionesTesis> {

    public WriterRelacionesTesis(@Qualifier("legalDataSource") DataSource dataSource){
        setDataSource(dataSource);
        setSql("INSERT INTO relaciones_tesis (id_seccion_tesis,id_tesis_relacion,id_seccion_documento,id_relacion_simple,id_documento_real) "+
        "VALUES (:idSeccionTesis, :idTesisRelacion, :idSeccionDocumento, :idRelacionSimple, :idDocumentoReal) ");
        setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<RelacionesTesis>());
    }
    
}
