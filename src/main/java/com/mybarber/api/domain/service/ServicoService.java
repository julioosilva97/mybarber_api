package com.mybarber.api.domain.service;

import java.util.List;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Servico;

public interface ServicoService {

	public List<Servico> listar(Barbearia barbearia);

	public void salvar(Servico servico);

	public void excluir(int id);

	public void atualizar(Servico servico);

	public Servico buscarPorIdServico(int id);
	
	

}
