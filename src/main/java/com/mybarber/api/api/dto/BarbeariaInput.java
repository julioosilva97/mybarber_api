package com.mybarber.api.api.dto;

import javax.validation.constraints.NotBlank;

import com.mybarber.api.domain.entity.Endereco;

public class BarbeariaInput {

    @NotBlank
	private String nome;


	
    @NotBlank
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
    
    
}
