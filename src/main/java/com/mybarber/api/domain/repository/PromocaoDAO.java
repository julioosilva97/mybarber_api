package com.mybarber.api.domain.repository;

import com.mybarber.api.domain.entity.Promocao;

public interface PromocaoDAO {

	
	public void salvar (Promocao promocao);
	
	public void editar (Promocao promocao);
	
	public void excluir (int id);
	
	public Promocao status(int idServico);
	
	public Promocao buscarPorIdServico(int idServico);
}
