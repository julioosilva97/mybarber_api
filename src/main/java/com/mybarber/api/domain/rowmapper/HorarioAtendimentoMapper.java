package com.mybarber.api.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.util.DiaDaSemana;

public class HorarioAtendimentoMapper implements RowMapper<HorarioAtendimento> {

	@Override
	public HorarioAtendimento mapRow(ResultSet rs, int rowNum) throws SQLException {

      
    	   return new HorarioAtendimento(rs.getInt("id"), 
    			   rs.getBoolean("aberto"),
    			   DiaDaSemana.valueOf(rs.getString("dia")),
    			   rs.getBoolean("aberto") ? rs.getTime("entrada").toLocalTime() : null,
    			   rs.getBoolean("aberto") ? rs.getTime("saida").toLocalTime() : null,
    			   rs.getBoolean("aberto") ? rs.getTime("entrada_almoco").toLocalTime() : null,
    			   rs.getBoolean("aberto") ? rs.getTime("saida_almoco").toLocalTime() : null);
		
	}

}
