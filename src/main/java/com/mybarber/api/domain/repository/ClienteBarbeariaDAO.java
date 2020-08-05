package com.mybarber.api.domain.repository;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;

public interface ClienteBarbeariaDAO {

	public void cadastrar(Cliente cliente, Barbearia barberia);
	
	public void excluir(int id);
	
}
