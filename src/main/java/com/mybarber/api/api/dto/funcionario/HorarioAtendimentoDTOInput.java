package com.mybarber.api.api.dto.funcionario;

import java.time.LocalTime;

public class HorarioAtendimentoDTOInput {

	private boolean aberto;
	private int dia;
	private LocalTime inicio;
	private LocalTime fim;
	private int idFuncionario;
	
	public boolean isAberto() {
		return aberto;
	}
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public LocalTime getInicio() {
		return inicio;
	}
	public void setInicio(LocalTime inicio) {
		this.inicio = inicio;
	}
	public LocalTime getFim() {
		return fim;
	}
	public void setFim(LocalTime fim) {
		this.fim = fim;
	}
	public int getIdFuncionario() {
		return idFuncionario;
	}
	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}
	
	
}
