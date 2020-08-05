package com.mybarber.api.domain.repository;

import java.util.List;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Servico;

public interface AgendamentoServicoDAO {

	public void salvar(Agendamento agendamento);
	
	public List<Servico> buscarPorId(int idAgendamento);
	public void excluir(int idAgendamento);
}
