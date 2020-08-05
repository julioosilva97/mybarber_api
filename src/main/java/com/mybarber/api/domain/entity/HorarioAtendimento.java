package com.mybarber.api.domain.entity;

import java.time.LocalTime;

public class HorarioAtendimento {

	private int id;
	private boolean aberto;
	private int dia;
	private LocalTime inicio;
	private LocalTime fim;
	private Funcionario funcionario;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public HorarioAtendimento(int id, boolean aberto, int dia, LocalTime inicio, LocalTime fim) {
		super();
		this.id = id;
		this.aberto = aberto;
		this.dia = dia;
		this.inicio = inicio;
		this.fim = fim;
	}
	public HorarioAtendimento() {
	}
	
	
	
}


