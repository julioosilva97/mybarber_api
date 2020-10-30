package com.mybarber.api.domain.service;



import com.mybarber.api.domain.entity.Usuario;

public interface UsuarioService {

	public void alterarSenha(Usuario usuario);

	public Usuario buscarPorLogin(String login);
	
	public boolean verificarUsuario(String usuario);
	
	public boolean verificarEmail(String email);
	
	public void esqueceuSenha(String email);
	
	public Object buscarUsuarioLogado(String tipo);
}
