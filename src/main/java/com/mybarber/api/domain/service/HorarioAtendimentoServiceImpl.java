package com.mybarber.api.domain.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.repository.HorarioAtendimentoDAO;

@Service
@Transactional
public class HorarioAtendimentoServiceImpl implements HorarioAtendimentoService {

	@Autowired
	HorarioAtendimentoDAO horarioAtendimentoDAO;
	
	

	@Override
	public void salvarHorarioAtendimento(List<HorarioAtendimento> horarioAtendimento) {

		List<HorarioAtendimento> horarios = horarioAtendimentoDAO
				.buscarPorFuncionario(horarioAtendimento.get(0).getId());

		if (horarios.size() > 0) {

			horarioAtendimentoDAO.editar(horarioAtendimento);

		} else {
			horarioAtendimentoDAO.salvar(horarioAtendimento);
		}

	}

	@Override
	public List<HorarioAtendimento> buscarHorarioAtendimentoPorFuncionario(int idFuncionario) {

		return horarioAtendimentoDAO.buscarPorFuncionario(idFuncionario);
	}


}
