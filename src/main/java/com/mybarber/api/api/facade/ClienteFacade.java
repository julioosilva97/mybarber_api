package com.mybarber.api.api.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mybarber.api.api.dto.cliente.ClienteDTO;
import com.mybarber.api.domain.entity.Cliente;

public interface ClienteFacade {

	public void cadastrar(ClienteDTO clienteDTO,HttpServletRequest request);
	public ClienteDTO buscarPorid(int id);
	public void editar(ClienteDTO clienteDTO);
	public void excluir(int id);
	public List<ClienteDTO> listar(HttpServletRequest request);
	public ClienteDTO toDTO(Cliente cliente);
	public Cliente toDoMain(ClienteDTO clienteDTO);
	public List<ClienteDTO> toListDTO(List<Cliente> clientes);
}
