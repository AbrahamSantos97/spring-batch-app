package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.legal-schema")
    public DataSource legalDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @BatchDataSource
    public DataSource dataSource(){
            return DataSourceBuilder.create().build();
        }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(legalDataSource());
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        return new NamedParameterJdbcTemplate(jdbcTemplate());
    }

}
