package com.mybarber.api.domain.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Servico;

@Repository
public class AgendamentoServicoDAOImpl implements AgendamentoServicoDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	String salvar = "INSERT INTO agendamento_servico(id_agendamento, id_servico) VALUES (?, ?)";
	
	String buscarPorId = "select s.* from agendamento_servico  inner join servico s on agendamento_servico.id_servico = s.id where agendamento_servico.id_agendamento = ?";
	
	String excluir = "delete from agendamento_servico where id_agendamento = ?";
	
	@Override
	public void salvar(Agendamento agendamento) {
		
		//agendamento.getServicos().forEach(servico-> jdbcTemplate.update(salvar,agendamento.getId(),servico.getId()));
		
		jdbcTemplate.batchUpdate(salvar, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, agendamento.getId());
				ps.setInt(2, agendamento.getServicos().get(i).getId());
			}
			
			@Override
			public int getBatchSize() {
				
				return agendamento.getServicos().size();
			}
		});
		
	}


	@Override
	public List<Servico> buscarPorId(int idAgendamento) {
		
		return jdbcTemplate.query(buscarPorId,new Object[] {idAgendamento},(rs,rowNum) -> new Servico(rs.getInt("id"),
				rs.getString("descricao"), rs.getFloat("valor"), rs.getTime("tempo").toLocalTime()));
	}


	@Override
	public void excluir(int idAgendamento) {
		
		jdbcTemplate.update(excluir,idAgendamento);
	}
 
	
}
