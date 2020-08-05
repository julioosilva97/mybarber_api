package com.mybarber.api.api.facade;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mybarber.api.api.dto.servico.ServicoDTO;
import com.mybarber.api.domain.entity.Servico;

public interface ServicoFacade {

	public void salvar(ServicoDTO servicoDTO,HttpServletRequest request);
	public void editar(ServicoDTO servicoDTO);
	public void excluir(int id);
	public List<ServicoDTO> listar(HttpServletRequest request);
	public ServicoDTO toDTO(Servico servico);
	public Servico toDoMain(ServicoDTO servicoDTO);
	public List<ServicoDTO> toListDTO(List<Servico> servicos);
	
}
