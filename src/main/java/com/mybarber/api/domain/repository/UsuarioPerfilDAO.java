package com.mybarber.api.domain.repository;


import com.mybarber.api.domain.entity.Usuario;

public interface UsuarioPerfilDAO {

	public void salvar(Usuario usuario);
	
	public void excluir(Usuario usuario);
	
	public void editar(Usuario usuario);
}
