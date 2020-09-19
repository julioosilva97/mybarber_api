package com.mybarber.api.domain.repository;

import com.mybarber.api.domain.entity.Usuario;

public interface UsuarioDAO {


	public void salvar(Usuario usuario);
	public Usuario buscar(int id);
	public void alterar(Usuario usuario);
	public void excluir(Usuario usuario);

	public void alterarSenha(Usuario usuario);

	public Usuario buscarPorLogin(String login);
	public Usuario buscarPorEmail(String email);
	public boolean verificarLogin(String login);
	public boolean verificarEmail(String email);
	/*public void alterarSenha(Usuario usuario);*/
	

}
