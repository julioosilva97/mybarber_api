package com.mybarber.api.api.dto.servico;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ServicoDTO {
	
	 private int id;
	private String descricao;
	private float valor;
	@JsonFormat(pattern = "HH:mm")
	private LocalTime tempo;
    @NotNull
    private Long idBarbearia;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public LocalTime getTempo() {
		return tempo;
	}
	public void setTempo(LocalTime tempo) {
		this.tempo = tempo;
	}
	
	public Long getIdBarbearia() {
		return idBarbearia;
	}
	public void setIdBarbearia(Long idBarbearia) {
		this.idBarbearia = idBarbearia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ServicoDTO() {
	}
	
}
