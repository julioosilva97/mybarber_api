package com.mybarber.api.api.facade;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mybarber.api.api.dto.agendamento.AgendamentoDTO;
import com.mybarber.api.api.dto.agendamento.AgendamentoDTOInput;
import com.mybarber.api.api.dto.agendamento.EventoRM;
import com.mybarber.api.api.dto.relatorio.RelatorioDTO;
import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Relatorio;

public interface AgendamentoFacade {

	public void salvar(AgendamentoDTOInput agendamentoDtoInput, HttpServletRequest request);
	public List<EventoRM> listarPorBarbeiro(int idBarbeiro);
	public AgendamentoDTO buscarPorId(int idAgendamento);
	public void editar(AgendamentoDTOInput agendamentoDtoInput, HttpServletRequest request);
	public void alterarStatus(AgendamentoDTO agendamentoDto);
	public Agendamento toDoMain(AgendamentoDTO agendamentoRM);
	public AgendamentoDTO toDTO(Agendamento agendamento);
	public Agendamento toDoMainInput(AgendamentoDTOInput agendamentoRMImput);
	public List<EventoRM> toListEventoDTO(List<Agendamento> agendamentos);
	public List<Agendamento> buscarPorData(LocalDate data,int idBarbeiro);
	public List<RelatorioDTO> somaValorMensal(HttpServletRequest request, LocalDate data);
	public List<RelatorioDTO> toListRelatorioDTO(List<Relatorio> relatorios);
	public RelatorioDTO toRelatorioDTO(Relatorio relatorio);
}
