package com.mybarber.api.domain.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Relatorio;
import com.mybarber.api.domain.repository.AgendamentoDAO;
import com.mybarber.api.domain.repository.AgendamentoServicoDAO;
import com.mybarber.api.domain.util.SituacaoAgendamento;

@Transactional
@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	private AgendamentoDAO agendamentoDAO;

	@Autowired
	private AgendamentoServicoDAO agendamentoSevicoDAO;

	@Override
	public void salvar(Agendamento agendamento) {

		agendamento.setStatus(SituacaoAgendamento.valueOf("AGENDADO"));
		agendamentoDAO.salvar(agendamento);
		agendamentoSevicoDAO.salvar(agendamento);

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
