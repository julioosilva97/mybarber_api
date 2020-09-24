package com.mybarber.api.domain.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Relatorio;
import com.mybarber.api.domain.exception.NegocioException;
import com.mybarber.api.domain.repository.AgendamentoDAO;
import com.mybarber.api.domain.repository.AgendamentoServicoDAO;
import com.mybarber.api.domain.repository.HorarioAtendimentoDAO;
import com.mybarber.api.domain.util.SituacaoAgendamento;

@Transactional
@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	private AgendamentoDAO agendamentoDAO;
	
	@Autowired
	private HorarioAtendimentoDAO horarioAtendimentoDAO;

	@Autowired
	private AgendamentoServicoDAO agendamentoSevicoDAO;

	@Override
	public void salvar(Agendamento agendamento) {
		
		//1 - horário de inicio não pode ser maior que o termino 
		//2- não pode ser antes de agora 
		//3- não pode fora do dia e hora de atendimento do barbeiro 
		//4 - não pode estar entre um inicio e fim de um agendamento do dia 
		
		var dataHorarioInicio = agendamento.getDataHorarioInicio();
		var dataHorarioFim = agendamento.getDataHorarioFim();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		if(dataHorarioInicio.isBefore(dataHorarioFim)) {
			verificarHorarioAtendimento(dataHorarioInicio,dataHorarioFim,agendamento.getFuncionario().getId());
			
		}else {
			throw new NegocioException("Data e horário de inicio "+dataHorarioInicio.format(formatter)+" maior que data e horário de término "+dataHorarioFim.format(formatter));
		}
		
		//1 :
		

		
		

		//usar o enum factory
		/*agendamento.setStatus(SituacaoAgendamento.valueOf("AGENDADO"));
		agendamentoDAO.salvar(agendamento);
		agendamentoSevicoDAO.salvar(agendamento);*/

	}
	
	private boolean verificarHorarioAtendimento(Agendamento agendamento) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		var horariosFuncionario = horarioAtendimentoDAO.buscarPorFuncionario(agendamento.getFuncionario().getId());
		
		
		var diaAtendimentoFuncionario = horariosFuncionario.stream()
				.filter(e -> e.getDia().getNumeroSemana() == agendamento.getDataHorarioInicio().getDayOfWeek().getValue())
				.collect(Collectors.toList()).get(0);
		
		if(diaAtendimentoFuncionario.isAberto()) {
			
			
			var horarioInicio = agendamento.getDataHorarioInicio().toLocalTime();
			var horarioFim = agendamento.getDataHorarioFim().toLocalTime();
			
			
		
			
		}else {
			throw new NegocioException("Dia "+dataHorarioInicio.format(formatter)+" está fechado");
		}
		
		
		var teste = "show";
		
		return true;
	}

	@Override
	public List<Agendamento> listarPorBarbeiro(int idBarbeiro) {

		List<Agendamento> agendamentos = agendamentoDAO.listarPorBarbeiro(idBarbeiro);
		agendamentos
				.forEach(agendamento -> agendamento.setServicos(agendamentoSevicoDAO.buscarPorId(agendamento.getId())));

		return agendamentos;
	}

	@Override
	public Agendamento buscarPorId(int idAgendamento) {

		Agendamento agendamento = agendamentoDAO.buscarPorId(idAgendamento);
		agendamento.setServicos(agendamentoSevicoDAO.buscarPorId(agendamento.getId()));

		return agendamento;
	}

	@Override
	public void editar(Agendamento agendamento) {

		agendamento.setStatus(SituacaoAgendamento.valueOf("AGENDADO"));
		agendamentoDAO.editar(agendamento);
		agendamentoSevicoDAO.excluir(agendamento.getId());
		agendamentoSevicoDAO.salvar(agendamento);
	}

	@Override
	public void alterarStatus(Agendamento agendamento) {

		agendamentoDAO.alterarStatus(agendamento);
	}

	@Override
	public List<Agendamento> buscarPorData(LocalDate data,int idBarbeiro) {

		return agendamentoDAO.buscarPorData(data,idBarbeiro);
	}

	@Override
	public List<Relatorio> somaValorMensal(Barbearia barbearia, LocalDate date) {

		LocalDate data = LocalDate.now();

		return agendamentoDAO.somaValorMensal(barbearia.getId(), data);
	}

}
