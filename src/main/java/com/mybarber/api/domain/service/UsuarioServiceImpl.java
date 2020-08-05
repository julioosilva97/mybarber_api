package com.mybarber.api.domain.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Usuario;
import com.mybarber.api.domain.repository.ClienteDAO;
import com.mybarber.api.domain.repository.FuncionarioDAO;
import com.mybarber.api.domain.repository.TokenDeVerificacaoDAO;
import com.mybarber.api.domain.repository.UsuarioDAO;
import com.mybarber.api.domain.util.EnviarEmail;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private EnviarEmail enviarEmail;

	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private FuncionarioDAO funcionarioDAO;
	
	@Autowired
	TokenDeVerificacaoService tokenService;
	
	@Autowired
	TokenDeVerificacaoDAO tokenDAO;

	@Override
	public Usuario buscarPorLogin(String login) {
		return usuarioDAO.buscarPorLogin(login);
	}

	public void alterarSenha(Usuario usuario) {
		
		if(tokenDAO.buscarPorIdUsuario(usuario.getId())!=null) {
			
			usuarioDAO.alterarSenha(usuario);
			tokenDAO.excluirPorIdUsuario(usuario.getId());
		}else {
			new Exception("Sem token");
		}

	}

	@Override
	public boolean verificarUsuario(String usuario) {

		if (buscarPorLogin(usuario) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void esqueceuSenha(String email) {

		if (funcionarioDAO.verificarEmail(email)) {

			var funcionario = funcionarioDAO.buscarPorEmail(email);
			var token = tokenService.criarToken(funcionario);
			
			enviarEmail.resetarSenha(funcionario,token);
			

		} else if (clienteDAO.verificarEmail(email)) {

		} else {

			// lan�ar execpition
		}
	}


	

}
