package com.mybarber.api.domain.service;

import java.util.List;

import com.mybarber.api.domain.entity.Barbearia;

import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.util.Cargo;

public interface FuncionarioService {

	public void salvar(Funcionario funcionario);

	public List<Funcionario> listar(Barbearia barbearia);

	public Funcionario buscar(int id);

	public void alterar(Funcionario funcionario);

	public void excluir(int id);

	public void salvarPrimeiroFuncionario(Funcionario funcionario);

	public List<Funcionario> listarPorCargo(Cargo cargo, Barbearia barbearia);
	public Funcionario buscarPorIdUsuario(int idUsuario);
	
	public boolean verificarEmail(String email);

}
