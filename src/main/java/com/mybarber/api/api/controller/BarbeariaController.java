package com.mybarber.api.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mybarber.api.api.util.ConverterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mybarber.api.api.dto.BarbeariaInput;
import com.mybarber.api.api.dto.BarbeariaModel;
import com.mybarber.api.api.dto.barbearia.BarbeariaDTO;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.service.BarbeariaService;



@RestController
@RequestMapping("api/barbearias")
public class BarbeariaController {

	@Autowired
	private BarbeariaService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> salvar(@Valid @RequestBody BarbeariaInput barbeariaInput,BindingResult result,RedirectAttributes attr) {
		
		Barbearia barbearia = toEntity(barbeariaInput);
		service.salvar(barbearia);
		return new ResponseEntity<Void>(HttpStatus.OK);
		
	}
	
	@GetMapping("listar")
	public ResponseEntity <List<BarbeariaModel>> listar(ModelMap model) {
		
		return new ResponseEntity<List<BarbeariaModel>>(toCollectionModel(service.listar()),HttpStatus.OK);
	}
	
	@GetMapping("/editar/{id}")
	public ResponseEntity<BarbeariaDTO>iniciarEdica(@PathVariable("id") int id) {
		
		var barbearia = service.buscarPorId(id);
		var barbeariaDTO = (BarbeariaDTO) ConverterDTO.toDTO(barbearia, Barbearia.class);
		return new ResponseEntity<BarbeariaDTO> (barbeariaDTO, HttpStatus.OK);
	}
	
	
	@PutMapping("/editar")
	public ResponseEntity<Void> editar(@Valid @RequestBody BarbeariaInput barbeariaDTO) {
		var barbearia = (Barbearia)ConverterDTO.toDoMain(barbeariaDTO, Barbearia.class);
		service.alterar(barbearia);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		//alterar na sess�o tbm
		
	}
	
	@GetMapping("/excluir/{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") int id) {
		service.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	private BarbeariaModel toModel(Barbearia barbearia) {
		return modelMapper.map(barbearia, BarbeariaModel.class);
	}
	
	private List<BarbeariaModel> toCollectionModel(List<Barbearia> barbearias){
		return barbearias.stream()
				.map(barbearia -> toModel(barbearia))
				.collect(Collectors.toList());
	}
	
	private Barbearia toEntity(BarbeariaInput barbeariaInput) {
		return modelMapper.map(barbeariaInput, Barbearia.class);
	}
}
