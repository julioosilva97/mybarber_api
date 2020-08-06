package com.mybarber.api.api.facade;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mybarber.api.api.dto.funcionario.BarbeiroDTO;
import com.mybarber.api.api.dto.funcionario.FuncionarioDTO;
import com.mybarber.api.api.dto.funcionario.HorarioAtendimentoDTOInput;
import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.service.FuncionarioService;
import com.mybarber.api.domain.service.HorarioAtendimentoService;
import com.mybarber.api.domain.util.Cargo;

@Component
public class FuncionarioFacadeImpl implements FuncionarioFacade{

	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	HorarioAtendimentoService horarioAtendimentoService;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public void salvar(FuncionarioDTO funcionarioDto, HttpServletRequest request) {
		
		Funcionario funcionario = toDoMain(funcionarioDto);
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		funcionario.setBarbearia(barbearia);
		
		//funcionarioService.salvar(funcionario);
		
	}

	@Override
	public List<FuncionarioDTO> listar(int idBarbearia) {

		var barbearia = new Barbearia();
		barbearia.setId(idBarbearia);
		
		return toListDTO(funcionarioService.listar(barbearia));
	}

	@Override
	public FuncionarioDTO buscar(int id) {
		
		return toDTO(funcionarioService.buscar(id));
	}

	@Override
	public void alterar(FuncionarioDTO funcionarioDto) {
		
		funcionarioService.alterar(toDoMain(funcionarioDto));
	}

	@Override
	public void excluir(int id) {
		
		funcionarioService.excluir(id);
		
	}

	@Override
	public void salvarPrimeiroFuncionario(Funcionario funcionario) {
		
		//funcionarioService.salvarPrimeiroFuncionario(funcionario);
		
	}

	@Override
	public List<FuncionarioDTO> listarPorCargo(Cargo cargo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void salvarHorarioAtendimento(List<HorarioAtendimentoDTOInput> horarios) {
		
		horarioAtendimentoService.salvarHorarioAtendimento(toListHorarioAtendimentoMain(horarios));
		
	}

	@Override
	public List<HorarioAtendimento> buscarHorarioAtendimentoPorFuncionario(int id) {
		
		return horarioAtendimentoService.buscarHorarioAtendimentoPorFuncionario(id);
	}

	@Override
	public List<Agendamento> buscarPorData(LocalDate data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario toDoMain(FuncionarioDTO funcionarioDto) {
		
		return modelMapper.map(funcionarioDto, Funcionario.class);
	}

	@Override
	public FuncionarioDTO toDTO(Funcionario funcionario) {
		
		return modelMapper.map(funcionario, FuncionarioDTO.class);
	}

	@Override
	public List<FuncionarioDTO> toListDTO(List<Funcionario> funcionarios) {
		
		return funcionarios.stream()
				.map(funcionario -> toDTO(funcionario))
				.collect(Collectors.toList());
	}

	@Override
	public BarbeiroDTO toBarbeiroDTO(Funcionario funcionario) {
		
		return modelMapper.map(funcionario, BarbeiroDTO.class);
	}

	@Override
	public List<BarbeiroDTO> listarBarbeiros(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Barbearia barbearia = (Barbearia) session.getAttribute("barbearia");
		
		
		return null ;
	}

	@Override
	public List<BarbeiroDTO> toListBarbeiroRM(List<Funcionario> funcionarios) {
		
		return funcionarios.stream()
				.map(funcionario->toBarbeiroDTO(funcionario))
				.collect(Collectors.toList());
	}

	@Override
	public HorarioAtendimento toHorarioAtendimentoMain(HorarioAtendimentoDTOInput horarioAtendimentoDTO) {
		
		return modelMapper.map(horarioAtendimentoDTO, HorarioAtendimento.class);
	}

	@Override
	public List<HorarioAtendimento> toListHorarioAtendimentoMain(List<HorarioAtendimentoDTOInput> horariosDTO) {
		
		return horariosDTO.stream()
				.map(horario ->toHorarioAtendimentoMain(horario) )
				.collect(Collectors.toList());
	}
	
	

}
