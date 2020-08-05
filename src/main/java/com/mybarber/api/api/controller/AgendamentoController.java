package com.mybarber.api.api.controller;


import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybarber.api.api.facade.AgendamentoFacade;
import com.mybarber.api.api.dto.agendamento.AgendamentoDTO;
import com.mybarber.api.api.dto.agendamento.AgendamentoDTOInput;
import com.mybarber.api.api.dto.agendamento.EventoRM;
import com.mybarber.api.api.dto.relatorio.RelatorioDTO;
import com.mybarber.api.domain.entity.Agendamento;

@RestController
@RequestMapping("api/agendamento")
public class AgendamentoController {

	@Autowired
	AgendamentoFacade facade;
	
	@PostMapping("cadastrar")
	public ResponseEntity<Void> salvar(@Valid @RequestBody AgendamentoDTOInput agendamentoDtoInput,
			HttpServletRequest request) {

		facade.salvar(agendamentoDtoInput, request);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/listarFullCalendar/{id}")
	public ResponseEntity<List<EventoRM>> listarPorBarbeiro(@PathVariable("id") int id){
		
		return new ResponseEntity<List<EventoRM>>(facade.listarPorBarbeiro(id),HttpStatus.OK);
	}
	
	@PatchMapping("/buscarPorId/{id}")
	public ResponseEntity<AgendamentoDTO> buscarPorId(@PathVariable("id") int id){
		
		return new ResponseEntity<AgendamentoDTO>(facade.buscarPorId(id),HttpStatus.OK);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Void> editar(@Valid @RequestBody AgendamentoDTOInput agendamentoDTOImput,
			HttpServletRequest request){
		facade.editar(agendamentoDTOImput, request);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}	
	
	@PostMapping("/alterarStatus")
	public ResponseEntity<Void> alterarStatus(@RequestBody AgendamentoDTO agendamentoDto){
		
		facade.alterarStatus(agendamentoDto);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	@GetMapping("/buscarPorData/{data}/{idBarbeiro}")
	public ResponseEntity<List<Agendamento>> buscarPorData(@PathVariable("data") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate data,@PathVariable("idBarbeiro") int idBarbeiro){
		

		return new ResponseEntity<List<Agendamento>>(facade.buscarPorData(data,idBarbeiro),HttpStatus.OK);
		
	}
	
	@GetMapping("somaValorMensal")
	@ResponseBody 
	public ResponseEntity<List<RelatorioDTO>> valorTotalMensal(HttpServletRequest request,LocalDate data){
	
		
	    return new ResponseEntity<List<RelatorioDTO>>(facade.somaValorMensal(request, data),HttpStatus.OK);
	   
	}
	
}
