package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services.reader;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.models.Tesis;

@Component
public class ReaderTesisSql extends JdbcCursorItemReader<Tesis> {

    public ReaderTesisSql(@Qualifier("legalDataSource")DataSource dataSource) {
        setDataSource(dataSource);
        setSql("SELECT id_tesis AS idTesis, registro_digital AS registroDigital, instancia, epoca, ntesis, fuente,"+
        " tipo, rubro FROM tesis_bot ORDER BY id_tesis DESC LIMIT 1000");
        BeanPropertyRowMapper<Tesis> rowMapper = new BeanPropertyRowMapper<Tesis>();
        rowMapper.setMappedClass(Tesis.class);
        setRowMapper(rowMapper);
    }

}
