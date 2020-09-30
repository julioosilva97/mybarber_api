package com.mybarber.api.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.mybarber.api.domain.repository.HorarioAtendimentoDAO;
import com.mybarber.api.domain.repository.ServicoDAO;
import com.mybarber.api.domain.util.SituacaoAgendamento;

@Transactional
@Service
public class AgendamentoServiceImpl implements AgendamentoService {

	@Autowired
	private AgendamentoDAO agendamentoDAO;

	@Autowired
	private HorarioAtendimentoDAO horarioAtendimentoDAO;

	@Autowired
	private ServicoDAO servicoDAO;

	@Override
	public void salvar(Agendamento agendamento) {

		// 1 - horário de inicio não pode ser maior que o termino
		// 2- não pode ser antes de agora
		// 3- não pode fora do dia e hora de atendimento do barbeiro
		// 4 - não pode estar entre um inicio e fim de um agendamento do dia

		var dataHorarioInicio = agendamento.getDataHorarioInicio();
		var dataHorarioFim = agendamento.getDataHorarioFim();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		if (dataHorarioInicio.isAfter(dataHorarioFim))
			throw new NegocioException("Data e horário de inicio " + dataHorarioInicio.format(formatter)
					+ " maior que data e horário de término " + dataHorarioFim.format(formatter));

		if (dataHorarioInicio.isBefore(LocalDateTime.now()))
			throw new NegocioException("Data e horário de inicio " + dataHorarioInicio.format(formatter)
					+ " não pode ser anterior ao horário atual");

		verificarHorarioAtendimento(agendamento);
		verificarAgendamentos(agendamento);

		agendamento.setStatus(SituacaoAgendamento.valueOf("AGENDADO"));
		agendamentoDAO.salvar(agendamento);

	}

	@Override
	public List<Agendamento> listarPorBarbeiro(int idBarbeiro) {

		List<Agendamento> agendamentos = agendamentoDAO.listarPorBarbeiro(idBarbeiro);
		agendamentos.forEach(
				agendamento -> agendamento.setServicos(servicoDAO.buscarPorIdAgendamento(agendamento.getId())));

		return agendamentos;
	}

	@Override
	public Agendamento buscarPorId(int idAgendamento) {

		Agendamento agendamento = agendamentoDAO.buscarPorId(idAgendamento);
		agendamento.setServicos(servicoDAO.buscarPorIdAgendamento(agendamento.getId()));

		return agendamento;
	}

	@Override
	public void editar(Agendamento agendamento) {

		var dataHorarioInicio = agendamento.getDataHorarioInicio();
		var dataHorarioFim = agendamento.getDataHorarioFim();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		if (dataHorarioInicio.isAfter(dataHorarioFim))
			throw new NegocioException("Data e horário de inicio " + dataHorarioInicio.format(formatter)
					+ " maior que data e horário de término " + dataHorarioFim.format(formatter));

		if (dataHorarioInicio.isBefore(LocalDateTime.now()))
			throw new NegocioException("Data e horário de inicio " + dataHorarioInicio.format(formatter)
					+ " não pode ser anterior ao horário atual");

		agendamento.setStatus(SituacaoAgendamento.valueOf("AGENDADO"));

		verificarHorarioAtendimento(agendamento);
		verificarAgendamentos(agendamento);

		agendamentoDAO.editar(agendamento);

	}

	@Override
	public void alterarStatus(int idAgendamento, SituacaoAgendamento status) {

		var agendamento = buscarPorId(idAgendamento);

		if (agendamento.getStatus() == SituacaoAgendamento.CONCLUIDO)
			throw new NegocioException("Você não pode alterar um agendamento já concluído");
		if (agendamento.getStatus() == SituacaoAgendamento.CANCELADO)
			throw new NegocioException("Você não pode alterar um agendamento já cancelado");

		if (status == SituacaoAgendamento.CONCLUIDO && agendamento.getDataHorarioFim().isAfter(LocalDateTime.now()))
			throw new NegocioException("Você não pode concluir um agendamento fora da data e horário agendado");

		agendamento.setStatus(status);
		agendamentoDAO.alterarStatus(agendamento);
	}

	@Override
	public List<Agendamento> buscarPorData(LocalDate data, int idBarbeiro) {

		return agendamentoDAO.buscarPorData(data, idBarbeiro);
	}

	@Override
	public List<Relatorio> somaValorMensal(Barbearia barbearia, LocalDate date) {

		LocalDate data = LocalDate.now();

		return agendamentoDAO.somaValorMensal(barbearia.getId(), data);
	}

	private void verificarAgendamentos(Agendamento agendamento) {

		var agendamentosDia = agendamentoDAO.buscarPorData(agendamento.getDataHorarioInicio().toLocalDate(),
				agendamento.getFuncionario().getId());

		var dataHorarioInicio = agendamento.getDataHorarioInicio();
		var dataHorarioFim = agendamento.getDataHorarioFim();

		agendamentosDia.forEach(element -> {

			var inicio = element.getDataHorarioInicio();
			var fim = element.getDataHorarioFim();

			DateTimeFormatter formatterDay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

			if (!dataHorarioInicio.isBefore(inicio) && !dataHorarioInicio.isAfter(fim)) {

				if (element.getId() == agendamento.getId())
					return;

				throw new NegocioException("Horário inicio " + dataHorarioInicio.format(formatterTime) + " inválido, "
						+ "já tem um agendamento no dia  " + dataHorarioInicio.format(formatterDay) + " de "
						+ inicio.format(formatterTime) + " até " + fim.format(formatterTime));
			}

			if (!dataHorarioFim.isBefore(inicio) && !dataHorarioFim.isAfter(fim)) {

				if (element.getId() == agendamento.getId())
					return;

				throw new NegocioException("Horário término " + dataHorarioFim.format(formatterTime) + " inválido, "
						+ "já tem um agendamento no dia  " + dataHorarioFim.format(formatterDay) + " de "
						+ inicio.format(formatterTime) + " até " + fim.format(formatterTime));
			}

		});

	}

	private void verificarHorarioAtendimento(Agendamento agendamento) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		var horariosFuncionario = horarioAtendimentoDAO.buscarPorFuncionario(agendamento.getFuncionario().getId());

		var diaAtendimentoFuncionario = horariosFuncionario.stream().filter(
				e -> e.getDia().getNumeroSemana() == agendamento.getDataHorarioInicio().getDayOfWeek().getValue())
				.collect(Collectors.toList()).get(0);

		if (diaAtendimentoFuncionario.isAberto()) {

			var horarioInicio = agendamento.getDataHorarioInicio().toLocalTime();
			var horarioFim = agendamento.getDataHorarioFim().toLocalTime();

			var entrada = diaAtendimentoFuncionario.getEntrada();
			var saida = diaAtendimentoFuncionario.getSaida();
			var saidaAlmoco = diaAtendimentoFuncionario.getSaidaAlmoco();
			var entradaAlmoco = diaAtendimentoFuncionario.getEntradaAlmoco();

			if (!horarioInicio.isBefore(entrada) && !horarioInicio.isAfter(saida)) {

				if (!horarioInicio.isBefore(saidaAlmoco) && !horarioInicio.isAfter(entradaAlmoco)) {

					throw new NegocioException("Horário de inicio  " + horarioInicio
							+ " fora do horário de atendimento, horário de almoço");
				}

			} else {
				throw new NegocioException("Horário de inicio  " + horarioInicio + " fora do horário de atendimento");
			}

			if (!horarioFim.isBefore(entrada) && !horarioFim.isAfter(saida)) {

				if (!horarioFim.isBefore(saidaAlmoco) && !horarioFim.isAfter(entradaAlmoco)) {

					throw new NegocioException(
							"Horário de término  " + horarioFim + " fora do horário de atendimento, horário de almoço");
				}

			} else {
				throw new NegocioException("Horário de término  " + horarioFim + " fora do horário de atendimento");
			}

		} else {
			throw new NegocioException("Dia " + agendamento.getDataHorarioInicio().format(formatter) + " está fechado");
		}

	}

}
