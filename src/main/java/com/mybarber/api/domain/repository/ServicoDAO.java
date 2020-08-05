package com.mybarber.api.domain.repository;

import java.util.List;

import com.mybarber.api.domain.entity.Servico;



public interface ServicoDAO {

	public List<Servico>listar(int id);
	
	public void salvar(Servico servico);
	
	public void excluir(int id);
	
	public void atualizar(Servico servico);
	
	public Servico buscarPorId(int id);
	
	
}
