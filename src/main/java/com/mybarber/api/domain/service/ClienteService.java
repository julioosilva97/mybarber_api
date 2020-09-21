package com.mybarber.api.domain.service;

import java.util.List;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;

public interface ClienteService {

	public void cadastrar(Cliente cliente, int idBarbearia);

	public Cliente buscarPorid(int id);

	public void editar(Cliente cliente);

	public void excluir(int id);

	public List<Cliente> listar(Barbearia barbearia);

}
