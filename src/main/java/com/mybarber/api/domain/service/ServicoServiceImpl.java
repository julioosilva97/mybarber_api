package com.mybarber.api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Servico;
<<<<<<< HEAD
import com.mybarber.api.domain.repository.PromocaoDAO;
=======
import com.mybarber.api.domain.repository.BarbeariaDAO;
>>>>>>> d42f37647784cb51500b25deb4d133d5a58c673c
import com.mybarber.api.domain.repository.ServicoDAO;


@Service @Transactional
public class ServicoServiceImpl implements ServicoService{

	@Autowired
	ServicoDAO dao;
	
	@Autowired
<<<<<<< HEAD
	PromocaoDAO promocaoDAO;
=======
    BarbeariaDAO daoBarbearia;
>>>>>>> d42f37647784cb51500b25deb4d133d5a58c673c
	
	@Override
	public List<Servico> listar(Barbearia barbearia) {
		
		var servicos = dao.listar(barbearia.getId());
		servicos.forEach(servico -> {
			var promocao = promocaoDAO.buscarPorIdServico(servico.getId());
			if(promocao!= null && promocao.isStatus() ) {
				
			}
		});
		return servicos;
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
	public Servico buscarPorIdServico(int id) {
		// TODO Auto-generated method stub
		return null;
	}



}
