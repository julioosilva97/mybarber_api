package com.mybarber.api.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybarber.api.api.facade.ServicoFacade;
import com.mybarber.api.api.dto.servico.ServicoDTO;

@RestController
@RequestMapping("api/servicos")
public class ServicoController {

	@Autowired
	private ServicoFacade facade;

	@PostMapping("cadastrar")
	public ResponseEntity<Void> salvar(@Valid @RequestBody ServicoDTO servicoDTO, HttpServletRequest request) {

		facade.salvar(servicoDTO, request);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@GetMapping("listar")
	public ResponseEntity<List<ServicoDTO>> listar(HttpServletRequest request) {

		return new ResponseEntity<List<ServicoDTO>>(facade.listar(request), HttpStatus.OK);
	}

	@PutMapping("editar")
	public ResponseEntity<Void> editar(@Valid @RequestBody ServicoDTO servicoDTO) {

		facade.editar(servicoDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("deletar/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") int id) {

		facade.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
