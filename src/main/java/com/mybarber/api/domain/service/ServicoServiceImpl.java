package com.mybarber.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Servico;
import com.mybarber.api.domain.repository.ServicoDAO;


@Service @Transactional
public class ServicoServiceImpl implements ServicoService{

	@Autowired
	ServicoDAO dao;
	
	@Override
	public List<Servico> listar(Barbearia barbearia) {
		
		return dao.listar(barbearia.getId());
	}

	@Override
	public void salvar(Servico servico) {
		
		
		dao.salvar(servico);
		
	}

	@Override
	public void excluir(int id) {
		dao.excluir(id);
		
	}

	@Override
	public void atualizar(Servico servico) {
		dao.atualizar(servico);
		
	}

	@Override
	public Servico buscarPorId(int id) {
		return dao.buscarPorId(id);
	}

}
