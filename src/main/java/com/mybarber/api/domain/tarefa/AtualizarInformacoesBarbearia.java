package com.mybarber.api.domain.tarefa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.repository.BarbeariaDAO;
import com.mybarber.api.domain.repository.ClienteDAO;
import com.mybarber.api.domain.repository.FuncionarioDAO;
import com.mybarber.api.domain.repository.ServicoDAO;

@Component
public class AtualizarInformacoesBarbearia {
	
	@Autowired
	BarbeariaDAO barbeariaDAO;
	
	@Autowired
	ServicoDAO servicoDAO;
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	FuncionarioDAO funcionarioDAO;
	
	//@Scheduled(fixedDelay = 50000)
	@Transactional
	private void atualizarInformacoes() {
		
		var barbearias = barbeariaDAO.listar();
		
		barbearias.forEach(barbearia -> {
			
			var idBarbearia = barbearia.getId();
			
			 barbearia.setQtdServico(servicoDAO.listar(idBarbearia).size());
			 barbearia.setQtdCliente(clienteDAO.countPorBarbearia(idBarbearia));
			 barbearia.setQtdFuncionario(funcionarioDAO.listar(idBarbearia).size());
			 
			 barbeariaDAO.alterar(barbearia);
			 
			 System.out.print("Foi");
			 
		});
	}

}
