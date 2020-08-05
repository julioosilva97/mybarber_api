package com.mybarber.api.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mybarber.api.domain.entity.Servico;

public class ServicoMapper implements RowMapper<Servico>{

	@Override
	public Servico mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return  new Servico(rs.getInt("id"), rs.getString("descricao"),
				rs.getFloat("valor"), rs.getTime("tempo").toLocalTime());
	}

}
