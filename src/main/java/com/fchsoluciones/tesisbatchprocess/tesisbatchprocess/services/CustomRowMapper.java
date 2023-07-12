package com.fchsoluciones.tesisbatchprocess.tesisbatchprocess.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;

public class CustomRowMapper implements RowMapper<Integer> {

    @Override
    public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Optional.ofNullable(rs.getInt(rowNum)).orElse(null);
        // TODO Auto-generated method stub
        
    }
    
}
