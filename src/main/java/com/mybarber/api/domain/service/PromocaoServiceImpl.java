package com.mybarber.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Promocao;
import com.mybarber.api.domain.repository.PromocaoDAO;

@Service@Transactional
public class PromocaoServiceImpl implements PromocaoService {
	
	@Autowired
	PromocaoDAO dao;

	@Override
	public void salvar(Promocao promocao) {
		dao.salvar(promocao);
	}

}
