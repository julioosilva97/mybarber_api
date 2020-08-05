package com.mybarber.api.domain.entity;

import java.time.LocalDate;

public class Cliente extends Pessoa {

	public Cliente() {
	}

	public Cliente(int id, String nome, String telefone, String email, LocalDate dataNascimento, Endereco endereco,
			Usuario usuario) {
		super(id, nome, telefone, email, dataNascimento, endereco, usuario);
		
	}

	public Cliente(int id, String nome) {
		super(id, nome);
	}
	
	
	
}
