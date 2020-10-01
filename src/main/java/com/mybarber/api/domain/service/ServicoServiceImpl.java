package com.mybarber.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Servico;
import com.mybarber.api.domain.repository.BarbeariaDAO;
import com.mybarber.api.domain.repository.ServicoDAO;


@Service @Transactional
public class ServicoServiceImpl implements ServicoService{

	@Autowired
	ServicoDAO dao;
	
	@Autowired
    BarbeariaDAO daoBarbearia;
	
	@Override
	public List<Servico> listar(Barbearia barbearia) {
		
		var show = dao.listar(barbearia.getId());
		return show;
	}

	@Override
	public void salvar(Servico servico) {
		
		dao.salvar(servico);
		
		var barbearia = daoBarbearia.buscarPorId(servico.getBarbearia().getId());
		
		barbearia.setQtdCliente(barbearia.getQtdServico()+1);
		
		daoBarbearia.alterar(barbearia);
		
		
	}

	@Override
	public void excluir(int id) {
		
		var servico = buscarPorId(id);
		
		var barbearia = daoBarbearia.buscarPorId(servico.getBarbearia().getId());
		
		barbearia.setQtdCliente(barbearia.getQtdServico()-1);
		
		daoBarbearia.alterar(barbearia);
		
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
