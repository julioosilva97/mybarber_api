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

	    if (!promocao.getDataInicio().isAfter(promocao.getDataFim())) {
	        if (!promocao.getDataInicio().isBefore(LocalDate.now())) {
		        dao.salvar(promocao);
		    } else {
		        throw new NegocioException("Data promoção não pode ser antes que o dia atual.");
		    }
	    }else {
	    	throw new NegocioException("Data inicial da promoção não pode ser após a data final.");
	    }
	  
	}
	

	@Override
	public void editar(Promocao promocao) {
		 if (!promocao.getDataInicio().isAfter(promocao.getDataFim())) {
		        if (!promocao.getDataInicio().isBefore(LocalDate.now())) {
			        dao.editar(promocao);
			    } else {
			        throw new NegocioException("Data promoção não pode ser antes que o dia atual.");
			    }
		    }else {
		    	throw new NegocioException("Data inicial da promoção não pode ser após a data final.");
		    }
	}


	@Override
	public Promocao buscarPromocao(int idServico) {
		return dao.buscarPorIdServico(idServico);
	}


}
