package com.mybarber.api.domain.repository;

import java.util.List;

import com.mybarber.api.domain.entity.Barbearia;




public interface BarbeariaDAO {

    public Barbearia salvar(Barbearia barbearia);
	public List<Barbearia> listar();
	public Barbearia buscarPorId(int id);
	public void alterar( Barbearia barbearia);
	public void excluir(int id);
}
