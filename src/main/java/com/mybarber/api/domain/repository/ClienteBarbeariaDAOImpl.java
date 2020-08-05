package com.mybarber.api.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;

@Repository
public class ClienteBarbeariaDAOImpl implements ClienteBarbeariaDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	String salvar = "insert into cliente_barbearia values (?,?)";
	
	String excluir = "delete from CLIENTE_BARBEARIA WHERE ID_CLIENTE  = ? ";
	
	@Override
	public void cadastrar(Cliente cliente, Barbearia barberia) {
		
		jdbcTemplate.update(salvar,cliente.getId(),barberia.getId());
	}
	
	@Override
	public void excluir(int id) {
		
		jdbcTemplate.update(excluir, id);	
		
	}

}
