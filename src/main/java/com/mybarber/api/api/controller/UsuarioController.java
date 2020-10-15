package com.mybarber.api.api.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybarber.api.api.dto.usuario.UsuarioDTO;
import com.mybarber.api.domain.entity.TokenDeVerificacao;
import com.mybarber.api.domain.entity.Usuario;
import com.mybarber.api.domain.service.TokenDeVerificacaoService;
import com.mybarber.api.domain.service.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService service;
	
	 @Autowired
	 private TokenDeVerificacaoService tokenService;
	
	@GetMapping("verificarUsuario/{login}")
	public ResponseEntity<Boolean> verificarUsuario(@PathVariable("login") String login) {

		return new ResponseEntity<Boolean>(service.verificarUsuario(login) , HttpStatus.OK);
	}
	
	@GetMapping("verificarEmail/{email:.+}")
	public ResponseEntity<Boolean> verificarEmail(@PathVariable("email") String email) {
		

		return new ResponseEntity<Boolean>(service.verificarEmail(email) , HttpStatus.OK);
	}
	
	@GetMapping("esqueceu-senha/{email:.+}")
	public ResponseEntity<Void> esqueceuSenha(@PathVariable("email") String email) {
		
		
		service.esqueceuSenha(email);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("buscar-token/{token}")
	public ResponseEntity<TokenDeVerificacao> buscarToken(@PathVariable("token") String token) throws Exception{
		
		 var mToken = tokenService.validarToken(token);
	     
		
		return new ResponseEntity<TokenDeVerificacao>(mToken , HttpStatus.OK);
		
	}
	
	
	@PostMapping("alterar-senha")
	public ResponseEntity<Void> alterarSenha(@RequestBody UsuarioDTO usuarioDTO){
		
		var usuario = new Usuario();
		
		usuario = usuario.toDoMain(usuarioDTO);
		
		service.alterarSenha(usuario);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
}
