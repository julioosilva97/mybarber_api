package com.mybarber.api.api.facade;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mybarber.api.api.dto.funcionario.BarbeiroDTO;
import com.mybarber.api.api.dto.funcionario.FuncionarioDTO;
import com.mybarber.api.api.dto.funcionario.HorarioAtendimentoDTOInput;
import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.util.Cargo;

public interface FuncionarioFacade {

	public void salvar(FuncionarioDTO funcionarioDto, HttpServletRequest request);
	public List<FuncionarioDTO> listar(int idBarbeira);
	public FuncionarioDTO buscar(int id);
	public void alterar(FuncionarioDTO funcionarioDto);
	public void excluir(int id);
    public List<BarbeiroDTO> listarBarbeiros(HttpServletRequest request);
	//victor
	public void salvarPrimeiroFuncionario(Funcionario funcionario);

	public List<FuncionarioDTO> listarPorCargo(Cargo cargo, HttpServletRequest request);
	
	public void salvarHorarioAtendimento(List<HorarioAtendimentoDTOInput> horarios);
	public List<HorarioAtendimento> buscarHorarioAtendimentoPorFuncionario(int id);
	public List<Agendamento> buscarPorData(LocalDate data);
	public Funcionario toDoMain(FuncionarioDTO funcionarioDto);
	public FuncionarioDTO toDTO(Funcionario funcionario);
	public List<FuncionarioDTO> toListDTO (List<Funcionario> funcionarios);
	public BarbeiroDTO toBarbeiroDTO(Funcionario funcionario);
	public List<BarbeiroDTO> toListBarbeiroRM(List<Funcionario> funcionarios);
	public HorarioAtendimento toHorarioAtendimentoMain(HorarioAtendimentoDTOInput horarioAtendimentoDTO);
	public List<HorarioAtendimento> toListHorarioAtendimentoMain(List<HorarioAtendimentoDTOInput> horariosDTO);
}
