package com.mybarber.api.domain.service;


import java.util.List;


import com.mybarber.api.domain.entity.HorarioAtendimento;

public interface HorarioAtendimentoService {
	

	public void salvarHorarioAtendimento(List<HorarioAtendimento> horarioAtendimento);
	public List<HorarioAtendimento> buscarHorarioAtendimentoPorFuncionario(int idFuncionario);

}
