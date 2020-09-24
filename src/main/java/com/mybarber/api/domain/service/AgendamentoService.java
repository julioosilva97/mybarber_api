package com.mybarber.api.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Relatorio;


public interface AgendamentoService {

	public void salvar(Agendamento agendamento);
	public List<Agendamento> listarPorBarbeiro(int idBarbeiro);
	public Agendamento buscarPorId(int idAgendamento);
	public void editar(Agendamento agendamento);
	public void alterarStatus(Agendamento agendamento);
	public List<Agendamento> buscarPorData(LocalDate data,int idBarbeiro);
	public List<Relatorio>somaValorMensal(Barbearia barbearia, LocalDate data);
}
