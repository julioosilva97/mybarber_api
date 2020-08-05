
package com.mybarber.api.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybarber.api.api.facade.ClienteFacade;
import com.mybarber.api.api.dto.cliente.ClienteDTO;
import com.mybarber.api.domain.service.ClienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	ClienteFacade facade;
	
	@Autowired
	ClienteService service;

	@PostMapping("cadastrar")
	public ResponseEntity<Void> cadatrar(@RequestBody ClienteDTO clienteDTO, HttpServletRequest request) {

		facade.cadastrar(clienteDTO, request);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PatchMapping("/editar/{id}")
	public ResponseEntity<ClienteDTO> iniciarEdicao(@PathVariable("id") int id) {

		return new ResponseEntity<ClienteDTO>(facade.buscarPorid(id), HttpStatus.OK);
	}

	@PutMapping("/editar")
	public ResponseEntity<Void> editar(@RequestBody ClienteDTO clienteDTO) {

		facade.editar(clienteDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") int id) {

		facade.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("listar")
	public ResponseEntity<List<ClienteDTO>> listar(HttpServletRequest request) {
		
		return new ResponseEntity<List<ClienteDTO>>(facade.listar(request),HttpStatus.OK);
	}
	
	@GetMapping("verificarEmail/{email}")
	public ResponseEntity<Boolean> verificarUsuario(@PathVariable("email") String email) {

		return new ResponseEntity<Boolean>(service.verificarEmail(email) , HttpStatus.OK);
	}
}
