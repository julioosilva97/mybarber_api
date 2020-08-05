package com.mybarber.api.api.facade;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybarber.api.api.dto.servico.ServicoDTO;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Servico;
import com.mybarber.api.domain.service.ServicoService;

@Component
public class ServicoFacadeImpl implements ServicoFacade {

	@Autowired
	private ServicoService service;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void salvar(ServicoDTO servicoDTO, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		Servico servico = toDoMain(servicoDTO);
		servico.setBarbearia(barbearia);

		service.salvar(servico);

	}

	@Override
	public void editar(ServicoDTO servicoDTO) {
		
		service.atualizar(toDoMain(servicoDTO));

	}

	@Override
	public void excluir(int id) {

		service.excluir(id);

	}

	@Override
	public List<ServicoDTO> listar(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		
		
		return toListDTO(service.listar(barbearia));
	}

	@Override
	public ServicoDTO toDTO(Servico servico) {

		return modelMapper.map(servico, ServicoDTO.class);
	}

	@Override
	public Servico toDoMain(ServicoDTO servicoDTO) {

		return modelMapper.map(servicoDTO, Servico.class);
		
	}

	@Override
	public List<ServicoDTO> toListDTO(List<Servico> servicos) {

		return servicos.stream().map(servico -> toDTO(servico)).collect(Collectors.toList());
	}
}
