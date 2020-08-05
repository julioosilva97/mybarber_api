package com.mybarber.api.domain.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;
import com.mybarber.api.domain.repository.ClienteBarbeariaDAO;
import com.mybarber.api.domain.repository.ClienteDAO;

@Service @Transactional
public class ClienteServiceImpl implements ClienteService {

	
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	ClienteBarbeariaDAO clienteBarbeariaDAO;
	
	@Override
	public void cadastrar(Cliente cliente,Barbearia barbearia) {
		
		
		
		clienteDAO.cadastrar(cliente);
		
		if(barbearia!=null) {
			clienteBarbeariaDAO.cadastrar(cliente, barbearia);
		}
		
	}

	@Override
	public Cliente buscarPorid(int id) {
		
		return clienteDAO.buscarPorid(id);
	}

	@Override
	public void editar(Cliente cliente) {
		
		clienteDAO.editar(cliente);
		
	}

	@Override
	public void excluir(int id) {
		
		
		clienteBarbeariaDAO.excluir(id);
		clienteDAO.excluir(id);
		
	}

	@Override
	public List<Cliente> listar(Barbearia barbearia) {
		
		return clienteDAO.listar(barbearia);
	}

	@Override
	public boolean verificarEmail(String email) {
		
		return clienteDAO.verificarEmail(email);
	}

}
