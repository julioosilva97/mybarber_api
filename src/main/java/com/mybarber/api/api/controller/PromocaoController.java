package com.mybarber.api.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybarber.api.api.dto.promocao.PromocaoDTO;
import com.mybarber.api.api.util.ConverterDTO;
import com.mybarber.api.domain.entity.Promocao;
import com.mybarber.api.domain.service.PromocaoService;

@RestController
@RequestMapping("api/promocao")
public class PromocaoController {

	
	@Autowired
	private PromocaoService service;
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody PromocaoDTO promocaoDTO) {
		
		Promocao promocao = (Promocao) ConverterDTO.toDoMain(promocaoDTO, Promocao.class);
		
		service.salvar(promocao);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
