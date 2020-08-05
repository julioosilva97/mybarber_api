package com.mybarber.api.api.dto.barbearia;

import com.mybarber.api.api.dto.endereco.EnderecoDTO;
import com.mybarber.api.domain.entity.Endereco;

import javax.validation.constraints.NotBlank;

public class Barbeariainput {

    private int id;
    @NotBlank
    private String nome;
    private String descricao;
    private EnderecoDTO endereco;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
