package com.mybarber.api.domain.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.rowmapper.HorarioAtendimentoMapper;

@Repository
public class HorarioAtendimentoDAOImpl implements HorarioAtendimentoDAO {

	
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	

	String salvar = "INSERT INTO horario_atendimento(dia, aberto, entrada, saida, id_funcionario) VALUES ( ?, ?, ?, ?, ?)";

	
	String excluir = "delete from horario_atendimento where id_funcionario =? "; 

	@Override
	public void salvar(List<HorarioAtendimento> horarioAtendimento) {

		jdbcTemplate.batchUpdate(salvar, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, horarioAtendimento.get(i).getDia().toString());
				ps.setBoolean(2, horarioAtendimento.get(i).isAberto());
				if (horarioAtendimento.get(i).isAberto() == true) {
					ps.setTime(3, java.sql.Time.valueOf(horarioAtendimento.get(i).getEntrada()));
					ps.setTime(4, java.sql.Time.valueOf(horarioAtendimento.get(i).getSaida()));
				} else {
					ps.setNull(3, Types.TIME);
					ps.setNull(4, Types.TIME);
				}
				ps.setInt(5, horarioAtendimento.get(i).getFuncionario().getId());

			}

			@Override
			public int getBatchSize() {

				return horarioAtendimento.size();
			}
		});

	}

	@Override
	public List<HorarioAtendimento> buscarPorFuncionario(int idFuncionario) {

		String buscarPorFuncionario = "select * from horario_atendimento where id_funcionario =  ?";
		
		return jdbcTemplate.query(buscarPorFuncionario, new Object[] { idFuncionario }, new HorarioAtendimentoMapper());
	}

	@Override
	public void editar(List<HorarioAtendimento> horarioAtendimento) {

		String editar = "UPDATE horario_atendimento SET aberto=?, entrada=?, saida=? WHERE id_funcionario = ? and dia=?";

	
		jdbcTemplate.batchUpdate(editar, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				
				ps.setBoolean(1, horarioAtendimento.get(i).isAberto());
				if (horarioAtendimento.get(i).isAberto() == true) {
					ps.setTime(2, java.sql.Time.valueOf(horarioAtendimento.get(i).getEntrada()));
					ps.setTime(3, java.sql.Time.valueOf(horarioAtendimento.get(i).getSaida()));
				} else {
					ps.setNull(2, Types.TIME);
					ps.setNull(3, Types.TIME);
				}
				ps.setInt(4, horarioAtendimento.get(i).getFuncionario().getId());
				ps.setString(5, horarioAtendimento.get(i).getDia().toString());

			}

			@Override
			public int getBatchSize() {

				return horarioAtendimento.size();
			}
		});

	}

	@Override
	public void excluir(int idFuncionario) {
		
		jdbcTemplate.update(excluir,idFuncionario);
		
	}

}
