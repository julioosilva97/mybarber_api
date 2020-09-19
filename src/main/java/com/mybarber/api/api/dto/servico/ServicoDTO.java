package com.mybarber.api.api.dto.servico;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.mybarber.api.api.util.FlexibleFloatDeserializer;

//RM = represetation Model

public class ServicoDTO {
	
	 private int id;
	private String descricao;
	private float valor;
	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime tempo;
	
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
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ServicoDTO() {
	}
	
}
