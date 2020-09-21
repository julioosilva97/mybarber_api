
package com.mybarber.api.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.mybarber.api.api.util.ConverterDTO;
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

import com.mybarber.api.api.dto.cliente.ClienteDTO;
import com.mybarber.api.api.dto.cliente.ClienteInput;
import com.mybarber.api.api.dto.funcionario.FuncionarioDTO;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;
import com.mybarber.api.domain.service.ClienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@PostMapping
	public ResponseEntity<Void> cadatrar(@RequestBody ClienteInput clienteInput) {
		
		var idBarberia = clienteInput.getIdBarbearia();
		
		var cliente = (Cliente) ConverterDTO.toDoMain(clienteInput, Cliente.class);

		service.cadastrar(cliente, idBarberia);

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PatchMapping("{id}")
	public ResponseEntity<ClienteDTO> iniciarEdicao(@PathVariable("id") int id) {
		var cliente = service.buscarPorid(id);

		var clienteDTO = (ClienteDTO)ConverterDTO.toDTO(cliente, ClienteDTO.class);
		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Void> editar(@RequestBody ClienteInput clienteDTO) {
		
		var cliente = (Cliente)ConverterDTO.toDoMain(clienteDTO, Cliente.class);
		service.editar(cliente);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> excluir(@PathVariable("id") int id) {

		service.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@GetMapping("{idBarbearia}")
	public ResponseEntity<List<ClienteInput>> listar(@PathVariable ("idBarbearia") int idBarbearia) {
		var barbearia = new Barbearia();
		barbearia.setId(idBarbearia);
		
		var clientes = service.listar(barbearia);
		var clienteDTO = clientes.stream()
				                 .map(doMain -> (ClienteInput) ConverterDTO.toDTO(doMain, ClienteInput.class))
				                 .collect(Collectors.toList());
		
		return new ResponseEntity<List<ClienteInput>>(clienteDTO,HttpStatus.OK);
	}
	
}
