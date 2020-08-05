package com.mybarber.api.api.facade;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybarber.api.api.dto.agendamento.AgendamentoDTO;
import com.mybarber.api.api.dto.agendamento.AgendamentoDTOInput;
import com.mybarber.api.api.dto.agendamento.EventoRM;
import com.mybarber.api.api.dto.relatorio.RelatorioDTO;
import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Relatorio;
import com.mybarber.api.domain.enumfactory.AgendamentoAgendado;
import com.mybarber.api.domain.enumfactory.StatusAgendamento;
import com.mybarber.api.domain.service.AgendamentoService;

@Component
public class AgendamentoFacadeImpl implements AgendamentoFacade{

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	AgendamentoService agendamentoService;
	
	@Override
	public void salvar(AgendamentoDTOInput agendamentoDtoInput, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		Agendamento agendamento = toDoMainInput(agendamentoDtoInput);
		agendamento.setBarbearia(barbearia);
		
		agendamentoService.salvar(agendamento);
		
	}

	@Override
	public List<EventoRM> listarPorBarbeiro(int idBarbeiro) {
		
		return toListEventoDTO(agendamentoService.listarPorBarbeiro(idBarbeiro)) ;
	}

	@Override
	public AgendamentoDTO buscarPorId(int idAgendamento) {
		
		return toDTO(agendamentoService.buscarPorId(idAgendamento));
	}

	@Override
	public void editar(AgendamentoDTOInput agendamentoDtoInput, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		
		Agendamento agendamento = toDoMainInput(agendamentoDtoInput);
		agendamento.setBarbearia(barbearia);
		agendamentoService.editar(agendamento);
	}

	@Override
	public void alterarStatus(AgendamentoDTO agendamentoDto) {
		
		agendamentoService.alterarStatus(toDoMain(agendamentoDto));
	}

	@Override
	public Agendamento toDoMain(AgendamentoDTO agendamentoRM) {
		
        StatusAgendamento status = StatusAgendamento.valueOf(agendamentoRM.getStatus().getDescricao());
		
		Agendamento agendamentoStatus = status.obterStatus();
		
		return modelMapper.map(agendamentoRM, agendamentoStatus.getClass());
	}

	@Override
	public AgendamentoDTO toDTO(Agendamento agendamento) {
		
		return modelMapper.map(agendamento,AgendamentoDTO.class);
	}

	@Override
	public Agendamento toDoMainInput(AgendamentoDTOInput agendamentoRMImput) {
		
		return modelMapper.map(agendamentoRMImput, AgendamentoAgendado.class);
	}

	@Override
	public List<EventoRM> toListEventoDTO(List<Agendamento> agendamentos) {
		
		return agendamentos.stream()
				.map(agendamento ->  new EventoRM(agendamento.getId(), agendamento.getCliente().getNome() , agendamento.gerarColor(),
						agendamento.getDataHorarioInicio(), agendamento.getDataHorarioFim()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Agendamento> buscarPorData(LocalDate data,int idBarbeiro) {
		
		return agendamentoService.buscarPorData(data,idBarbeiro);
	}

	@Override
	public List<RelatorioDTO> somaValorMensal(HttpServletRequest request, LocalDate data) {
		
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		
		return toListRelatorioDTO(agendamentoService.somaValorMensal(barbearia, data));
	}

	@Override
	public List<RelatorioDTO> toListRelatorioDTO(List<Relatorio> relatorios) {
		
		return relatorios.stream()
				.map(relatorio->toRelatorioDTO(relatorio))
				.collect(Collectors.toList());
	}

	@Override
	public RelatorioDTO toRelatorioDTO(Relatorio relatorio) {
		
		return modelMapper.map(relatorio, RelatorioDTO.class);
	}

}
