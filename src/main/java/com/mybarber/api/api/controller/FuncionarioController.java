package com.mybarber.api.api.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mybarber.api.api.dto.funcionario.FuncionarioInput;
import com.mybarber.api.domain.util.ConverterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mybarber.api.api.facade.FuncionarioFacade;
import com.mybarber.api.api.dto.funcionario.BarbeiroDTO;
import com.mybarber.api.api.dto.funcionario.FuncionarioDTO;
import com.mybarber.api.api.dto.funcionario.HorarioAtendimentoDTOInput;
import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.service.FuncionarioService;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

	
	@Autowired
	FuncionarioService service;

	@Autowired
	FuncionarioFacade facade;

	@Autowired
	ModelMapper modelMapper;


	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody FuncionarioInput funcionarioDto) {

		var funcionario = (Funcionario) ConverterDTO.toDoMain(funcionarioDto,Funcionario.class);

		service.salvar(funcionario);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Void> editar(@Valid @RequestBody FuncionarioInput funcionarioDto) {

		var funcionario = (Funcionario) ConverterDTO.toDoMain(funcionarioDto,Funcionario.class);

		service.alterar(funcionario);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping("salvar-primeiro-funcionario")
	public ResponseEntity<Void> salvarPrimeiroFuncionario(@RequestBody Funcionario funcionario,
			HttpServletRequest request) {
		facade.salvarPrimeiroFuncionario(funcionario);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("listar/{idBarbearia}")
	public ResponseEntity<List<FuncionarioDTO>> listar(@PathVariable("idBarbearia") int idBarbearia) {

		return new ResponseEntity<List<FuncionarioDTO>>(facade.listar(idBarbearia), HttpStatus.OK);
	}

	// listar somente barbeiros de determinada barbearia
	@GetMapping("listarBarbeiros")
	public ResponseEntity<List<BarbeiroDTO>> listarBarbeiros(HttpServletRequest request) {

		return new ResponseEntity<List<BarbeiroDTO>>(facade.listarBarbeiros(request), HttpStatus.OK);
	}

	@PatchMapping("/editar/{id}")
	public ResponseEntity<FuncionarioDTO> iniciarEdicao(@PathVariable("id") int id) {

		return new ResponseEntity<FuncionarioDTO>(facade.buscar(id), HttpStatus.OK);
	}



	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") int id) {

		facade.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/horarioAtendimento")
	public ResponseEntity<Void> defirnirHorarioAtendimento(@RequestBody List<HorarioAtendimentoDTOInput> horarios) {

		facade.salvarHorarioAtendimento(horarios);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("/buscarHorarioAtendimento/{id}")
	public ResponseEntity<List<HorarioAtendimento>> buscarHorarioAtendimentoPorFuncionario(@PathVariable("id") int id) {

		return new ResponseEntity<List<HorarioAtendimento>>(facade.buscarHorarioAtendimentoPorFuncionario(id),
				HttpStatus.OK);

	}
	
	@GetMapping("verificarEmail/{email}")
	public ResponseEntity<Boolean> verificarUsuario(@PathVariable("email") String email) {

		return new ResponseEntity<Boolean>(service.verificarEmail(email) , HttpStatus.OK);
	}
	

}
