package com.mybarber.api.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Promocao;

@Repository
public class PromocaoDAOImpl implements PromocaoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String save = "insert into promocao (nome, dataHoraInicio, dataHoraFim, id_servico) values (?, ?, ?,?) ";
	private String delete = "delete from promocao where id = ?";
	private String update = "update promocao set nome =?, dataHoraInicio = ?, dataHoraFim = ? where id =?";

	@Override
	public void salvar(Promocao promocao) {

		jdbcTemplate.update(save, promocao.getNome(), promocao.getDataHoraInicio(), promocao.getDataHoraFim(), promocao.getServico().getId());
	}

	@Override
	public void editar(Promocao promocao) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

	

}
