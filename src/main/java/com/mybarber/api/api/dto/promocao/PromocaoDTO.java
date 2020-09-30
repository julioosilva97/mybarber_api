package com.mybarber.api.api.dto.promocao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PromocaoDTO {

	private int id;

	private LocalDate dataInicio;
	
	private LocalDate dataFim;
	private String descricao;
	private Long idServico;
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
	public Long getIdServico() {
		return idServico;
	}
	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	public PromocaoDTO() {
	}
   
	
}
