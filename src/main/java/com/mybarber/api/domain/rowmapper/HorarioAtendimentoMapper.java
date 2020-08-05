package com.mybarber.api.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mybarber.api.domain.entity.HorarioAtendimento;

public class HorarioAtendimentoMapper implements RowMapper<HorarioAtendimento> {

	@Override
	public HorarioAtendimento mapRow(ResultSet rs, int rowNum) throws SQLException {

      
    	   return new HorarioAtendimento(rs.getInt("id"), rs.getBoolean("aberto"), rs.getInt("dia"),
    			   rs.getBoolean("aberto") ? rs.getTime("inicio").toLocalTime() : null,
    			   rs.getBoolean("aberto") ? rs.getTime("final").toLocalTime() : null);
		
	}

}
