package com.mybarber.api.domain.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mybarber.api.domain.entity.Promocao;
import com.mybarber.api.domain.entity.Servico;

public class ServicoMapper implements RowMapper<Servico>{

	@Override
	public Servico mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		var promocao = new Promocao();
		
		if(rs.getObject("id_promocao") != null) {
		
		return  new Servico(rs.getInt("id"), rs.getString("descricao"),
				rs.getFloat("valor"), rs.getTime("tempo").toLocalTime(), 
				new Promocao(rs.getInt("id"),rs.getDate("dataInicio").toLocalDate(), 
						rs.getDate("dataFim").toLocalDate(),rs.getString("descricao"),
						rs.getBoolean("status"), rs.getFloat("valor") ));
	}else {
		return  new Servico(rs.getInt("id"), rs.getString("descricao"),
				rs.getFloat("valor"), rs.getTime("tempo").toLocalTime(), null);
	}
		
	}

}
