package com.mybarber.api.domain.service;


import java.util.List;
import java.util.Map;

import com.mybarber.api.domain.entity.Barbearia;

import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.HorarioAtendimento;

public interface FuncionarioService {

	public void salvar(Map<String, Object> map);

	public List<Funcionario> listar(Barbearia barbearia);

	public Funcionario buscar(int id);

	public void alterar(Funcionario funcionario);

	public void excluir(int id);

	public List<Funcionario> listarPorCargo(Map<String, Object> map);

	public Funcionario buscarPorIdUsuario(int idUsuario);
	
	public boolean verificarEmail(String email);
	
	public void salvarHorarioAtendimento(List<HorarioAtendimento> horarioAtendimento);
	
	public List<HorarioAtendimento> buscarHorarioAtendimentoPorFuncionario(int idFuncionario);

}
