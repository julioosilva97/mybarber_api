package com.mybarber.api.domain.entity;



import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.mybarber.api.domain.util.Cargo;

@Component
public class Funcionario extends Pessoa {

	private Cargo cargo;
	
	private Barbearia barbearia;
	

	public Cargo getCargo() {
		return cargo;
	}


	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}


	public Barbearia getBarbearia() {
		return barbearia;
	}


	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}


	public Funcionario() {
	}

	public Funcionario(int id, String nome, String telefone,LocalDate dataNascimento, Endereco endereco,
			Usuario usuario, Cargo cargo, Barbearia barbearia) {
		super(id, nome, telefone, dataNascimento, endereco, usuario);
		this.cargo = cargo;
		this.barbearia = barbearia;
	}

	public Funcionario(int id, String nome, String telefone, Endereco endereco, Usuario usuario,
			Cargo cargo, Barbearia barbearia) {
		super(id, nome, telefone, endereco, usuario);
		this.cargo = cargo;
		this.barbearia = barbearia;
	}

	public Funcionario(int id, String nome, String telefone, Endereco endereco, Cargo cargo,
			Usuario usuario, Barbearia barbearia) {
		super(id, nome, telefone, endereco, usuario);
		this.cargo = cargo;
		this.barbearia = barbearia;
	}

	public Funcionario(int id) {
		super(id);
	}

}
