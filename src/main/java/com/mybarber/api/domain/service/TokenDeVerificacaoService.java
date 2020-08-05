package com.mybarber.api.domain.service;


import com.mybarber.api.domain.entity.Pessoa;
import com.mybarber.api.domain.entity.TokenDeVerificacao;

public interface TokenDeVerificacaoService {

	public TokenDeVerificacao criarToken(Pessoa pessoa);
	public TokenDeVerificacao validarToken(String token);
}
