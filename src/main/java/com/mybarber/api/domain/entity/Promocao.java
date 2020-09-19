package com.mybarber.api.domain.entity;

import java.time.LocalDateTime;

public class Promocao {

	private int id;
	private String nome;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private Servico servico;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	public Promocao(int id, String nome, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Servico servico) {
		this.id = id;
		this.nome = nome;
		this.dataHoraInicio = dataHoraInicio;
		this.dataHoraFim = dataHoraFim;
		this.servico = servico;
	}

	
	
	
	
	
}
