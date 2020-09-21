package com.mybarber.api.api.dto.promocao;

import java.time.LocalDateTime;
import java.util.Date;

public class PromocaoDTO {

	private int id;
	private Date dataInicio;
	private Date dataFim;
	private String descricao;
	private Long idServico;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
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
