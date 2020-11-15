package com.mybarber.api.domain.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Promocao;
import com.mybarber.api.domain.exception.NegocioException;
import com.mybarber.api.domain.repository.PromocaoDAO;

@Service@Transactional
public class PromocaoServiceImpl implements PromocaoService {
	
	@Autowired
	PromocaoDAO dao;

	@Override
	public void salvar(Promocao promocao) {

		validarDatasPromocao(promocao);

		if(promocao.getDataInicio().isAfter(LocalDate.now())) {
			promocao.setStatus(false);
		}else {
			promocao.setStatus(true);
		}
		dao.salvar(promocao);
	  
	}
	

	@Override
	public void editar(Promocao promocao) {

		validarDatasPromocao(promocao);

		if(promocao.getDataInicio().isAfter(LocalDate.now())) {
			promocao.setStatus(false);
		}else {
			promocao.setStatus(true);
		}

		dao.editar(promocao);
	}

	private void validarDatasPromocao(Promocao promocao){

		if (promocao.getDataInicio().isBefore(promocao.getDataFim()))
			throw new NegocioException("Data inicial da promoção não pode ser após a data final.");

		if (promocao.getDataInicio().isAfter(LocalDate.now()))
			throw new NegocioException("Data promoção não pode ser antes que o dia atual.");

	}


	@Override
	public Promocao buscarPromocao(int idServico) {

		return dao.buscarPorIdServico(idServico);
	}


	@Override
	public void alterarStatus(Promocao promocao) {
		dao.alterarStatus(promocao);
	}
	


}
