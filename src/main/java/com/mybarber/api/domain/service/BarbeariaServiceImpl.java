package com.mybarber.api.domain.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.repository.BarbeariaDAO;
import com.mybarber.api.domain.repository.EnderecoDAO;



@Service @Transactional
public class BarbeariaServiceImpl implements BarbeariaService {

	
	@Autowired
	EnderecoDAO daoEndereco;
	
	@Autowired
	BarbeariaDAO dao;
	
	@Override
	public void salvar(Barbearia barbearia) {
		barbearia.setEndereco(daoEndereco.salvar(barbearia.getEndereco()));
		dao.salvar(barbearia);
		
	}

	@Override
	public List<Barbearia> listar() {
		// TODO Auto-generated method stub
		return dao.listar();
	}

	@Override
	public void alterar(Barbearia barbearia) {
	
	daoEndereco.alterar(barbearia.getEndereco());
	dao.alterar(barbearia);
		
	}

	@Override
	public void excluir(int id) {
		dao.excluir(id);
		
	}

	@Override
	public Barbearia buscarPorId(int id) {
		// TODO Auto-generated method stub
		return dao.buscarPorId(id);
	}

	
}
