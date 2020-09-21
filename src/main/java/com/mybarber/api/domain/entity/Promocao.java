package com.mybarber.api.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Victor
 *
 */
public class Promocao {

	private int id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String descricao;
	private Servico servico;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Promocao(int id, LocalDate dataInicio, LocalDate dataFim, String descricao, Servico servico) {
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.descricao = descricao;
		this.servico = servico;
	}
	

	
	
}