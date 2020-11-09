package com.mybarber.api.domain.repository;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Relatorio;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface AgendamentoDAO {

	public void salvar(Agendamento agendamento);

	public List<Agendamento> listarPorBarbeiro(int idBarbeiro);

	public Agendamento buscarPorId(int idAgendamento);

	public void editar(Agendamento agendamento);

	public void alterarStatus(Agendamento agendamento);

	public List<Agendamento> buscarPorData(LocalDate data,int idBarbeiro);

	public List<Relatorio> somaValorMensal(int idBarbearia, LocalDate data);
	
	public void alterarNotificado(int idAgendamento);
	
	public Map<String, Integer> countStatusAgendamentoMes(int idBarbearia,String MM);

	public List<Map<String, String>> relatorioServicosMes(int idBarbearia);
	
}
