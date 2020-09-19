package com.mybarber.api.api.dto.promocao;

import java.time.LocalDateTime;

public class PromocaoDTO {

	private int id;
	private String nome;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
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
	public LocalDateTime getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDateTime getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}
	public PromocaoDTO(int id, String nome, LocalDateTime dataInicio, LocalDateTime dataFim) {
		this.id = id;
		this.nome = nome;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
	}
	
	
	
	
}
