package com.mybarber.api.domain.service;


import com.mybarber.api.domain.entity.Promocao;

public interface PromocaoService {

	
	public void salvar(Promocao promocao);
	public void editar(Promocao promocao);
	public Promocao buscarPromocao(int idServico);
	public void alterarStatus (Promocao promocao);
}
