package com.mybarber.api.domain.repository;



import com.mybarber.api.domain.entity.TokenDeVerificacao;

public interface TokenDeVerificacaoDAO {

	public TokenDeVerificacao  buscarPorToken(String token);

	public TokenDeVerificacao buscarPorIdUsuario(int id);
	
	public void excluirPorIdUsuario(int id);
	
	public void salvar(TokenDeVerificacao token);

}
