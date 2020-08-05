package com.mybarber.api.domain.builder;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;
import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.Servico;
import com.mybarber.api.domain.util.SituacaoAgendamento;
@EntityScan
public class AgendamentoBuilder {
	
	@Autowired
	private Agendamento agendamento;
	
	@Autowired
	private List<Servico> servicos;
	
	@Autowired
	private Cliente cliente;
	
	@Autowired
	private Funcionario barbeiro;
	
	@Autowired
	private Barbearia barbearia;

	public AgendamentoBuilder() {
	
	}
	
	public AgendamentoBuilder naDataEHorarioInicio(LocalDateTime dataHorarioInicio) {
		agendamento.setDataHorarioInicio(dataHorarioInicio);
		return this;
	}
	
	public AgendamentoBuilder naDataEHorarioDim(LocalDateTime dataHorarioFim) {
		agendamento.setDataHorarioFim(dataHorarioFim);
		return this;
	}
	
	public AgendamentoBuilder comCliente(int id) {
		cliente.setId(id);
		return this;
	}
	
	public AgendamentoBuilder comBarbeiro(int id){
         barbeiro.setId(id);
         return this;
	}
	
	public AgendamentoBuilder comServico(List<Integer> servicos) {
		
		for(Integer idServico : servicos ) {
			
			Servico servico = new Servico();
			servico.setId(idServico);
			this.servicos.add(servico);
		}
		
		return this;
	}
	
	public AgendamentoBuilder comObservacao(String observacao) {
		agendamento.setObservacao(observacao);
		return this;
	}
	
	public AgendamentoBuilder comValorTotal(Double valorTotal) {
		agendamento.setValor(valorTotal);
		return this;
	}
	
	public AgendamentoBuilder noStatus(String status) {
		
		agendamento.setStatus(SituacaoAgendamento.valueOf(status));
		return this;
	}
	
	public AgendamentoBuilder naBarbearia(int id) {
		barbearia.setId(id);
		return this;
	}
	
	public Agendamento controi() {
		agendamento.setCliente(cliente);
		agendamento.setFuncionario(barbeiro);
		agendamento.setServicos(servicos);
		agendamento.setBarbearia(barbearia);
		return agendamento;
	}

}
