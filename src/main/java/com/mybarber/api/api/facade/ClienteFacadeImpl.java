package com.mybarber.api.api.facade;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybarber.api.api.dto.cliente.ClienteDTO;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;
import com.mybarber.api.domain.service.ClienteService;

@Component
public class ClienteFacadeImpl implements ClienteFacade {

	@Autowired
	ClienteService service;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public void cadastrar(ClienteDTO clienteDTO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		
		service.cadastrar(toDoMain(clienteDTO), barbearia);
		
	}

	@Override
	public ClienteDTO buscarPorid(int id) {
		
		return toDTO(service.buscarPorid(id));
	}

	@Override
	public void editar(ClienteDTO clienteDTO) {
		
		 service.editar(toDoMain(clienteDTO)); 
		
	}

	@Override
	public void excluir(int id) {
		service.excluir(id);
	}

	@Override
	public List<ClienteDTO> listar(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		
 		return toListDTO(service.listar(barbearia));
	}

	@Override
	public ClienteDTO toDTO(Cliente cliente) {
		
		return modelMapper.map(cliente, ClienteDTO.class);
	}

	@Override
	public Cliente toDoMain(ClienteDTO clienteDTO) {
		
		return modelMapper.map(clienteDTO, Cliente.class);
	}

	@Override
	public List<ClienteDTO> toListDTO(List<Cliente> clientes) {
		
		return clientes.stream()
			   .map(cliente -> toDTO(cliente))
			   .collect(Collectors.toList());
	}

}
