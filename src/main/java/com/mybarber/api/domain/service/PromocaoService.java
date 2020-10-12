package com.mybarber.api.domain.service;

import java.util.List;

import com.mybarber.api.domain.entity.Promocao;
import com.mybarber.api.domain.entity.Servico;

public interface PromocaoService {

	
	public void salvar(Promocao promocao);
	public void editar(Promocao promocao);
	public Promocao buscarPromocao(int idServico);
}
