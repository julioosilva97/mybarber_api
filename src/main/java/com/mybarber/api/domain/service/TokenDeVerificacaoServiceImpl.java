package com.mybarber.api.domain.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybarber.api.api.exceptionhandler.ApiExceptionHandler;
import com.mybarber.api.domain.entity.Pessoa;
import com.mybarber.api.domain.entity.TokenDeVerificacao;
import com.mybarber.api.domain.exception.NegocioException;
import com.mybarber.api.domain.repository.TokenDeVerificacaoDAO;

@Service
public class TokenDeVerificacaoServiceImpl implements TokenDeVerificacaoService {

	@Autowired
	TokenDeVerificacaoDAO tokenDAO;

	@Override
	public TokenDeVerificacao criarToken(Pessoa pessoa) {
        
		tokenDAO.excluirPorIdUsuario(pessoa.getUsuario().getId());
		
		var token = new TokenDeVerificacao();
		token.setUsuario(pessoa.getUsuario());

		tokenDAO.salvar(token);

		return token;
	}

	@Override
	public TokenDeVerificacao validarToken(String token)  {

		var mToken = tokenDAO.buscarPorToken(token);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:m");
		
		
		if (mToken != null) {

			if (mToken.getDataHoraExpiracao().isBefore(LocalDateTime.now())) {

				throw new NegocioException("Token inspirado "+mToken.getDataHoraExpiracao().format(formatter)+", solicite um novo.");
				
			} else {
				
				
                return mToken;
			}

		} else {

			throw new NegocioException("Token inv�lido.");
		}

	}

}
