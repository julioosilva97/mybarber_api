package com.mybarber.api.domain.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mybarber.api.domain.entity.Barbearia;


public interface BarbeariaService {

	 public void salvar(Barbearia barbearia);
		public List<Barbearia> listar();
		public Barbearia buscarPorId(int id);
		public void alterar( Barbearia barbearia,HttpServletRequest request);
		public void excluir(int id);
}
