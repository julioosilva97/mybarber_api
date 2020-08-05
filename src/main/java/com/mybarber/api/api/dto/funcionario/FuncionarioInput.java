package com.mybarber.api.api.dto.funcionario;

import com.mybarber.api.api.dto.BarbeariaInput;
import com.mybarber.api.api.dto.endereco.EnderecoDTO;
import com.mybarber.api.api.dto.usuario.UsuarioDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class FuncionarioInput {

    private int id;
    @NotBlank
    private String nome;
    private String sobrenome;
    private String telefone;
    @Email
    private String email;
    private LocalDate dataNascimento;
    private EnderecoDTO endereco;
    @NotBlank
    private String cargo;
    @NotNull
    private UsuarioDTO usuario;
    @NotNull
    private Long idBarbearia;

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Long getIdBarbearia() {
        return idBarbearia;
    }

    public void setIdBarbearia(Long idBarbearia) {
        this.idBarbearia = idBarbearia;
    }
}
