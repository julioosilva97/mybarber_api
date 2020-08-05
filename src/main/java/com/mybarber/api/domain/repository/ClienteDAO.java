package com.mybarber.api.domain.repository;

import java.util.List;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;

public interface ClienteDAO {
	
	public void cadastrar(Cliente cliente);
	public Cliente buscarPorid(int id);
	public void editar(Cliente cliente);
	public void excluir(int id);
	public List<Cliente> listar(Barbearia barbearia);
	public boolean verificarEmail(String email);

}
