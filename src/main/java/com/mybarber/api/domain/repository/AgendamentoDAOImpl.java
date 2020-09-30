package com.mybarber.api.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.mybarber.api.domain.entity.Agendamento;
import com.mybarber.api.domain.entity.Relatorio;
import com.mybarber.api.domain.rowmapper.AgendamentoMapper;
import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;



@Repository
public class AgendamentoDAOImpl implements AgendamentoDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	
	String listarPorBarbeiro = "select a.id id_agendamento, a.datahorainicio, a.datahoratermino,a.observacao,a.status,a.valor,a.id_barbeiro," + 
			"c.id id_cliente," + 
			"a.nome_cliente" + 
			" from agendamento a" + 
			" left join cliente c on a.id_cliente = c.id "
			+ " inner join funcionario f on a.id_barbeiro = f.id" + 
			" where f.id = ?";
	
	String buscarPorId = "select a.id id_agendamento, a.datahorainicio, a.datahoratermino,a.observacao,a.status,a.valor,a.id_barbeiro," + 
			"c.id id_cliente," + 
			"a.nome_cliente" + 
			" from agendamento a" + 
			" left join cliente c on a.id_cliente = c.id" + 
			" inner join funcionario f on a.id_barbeiro = f.id" + 
			" where a.id = ?";
	
	
	String alterarStatus = "update agendamento set status = ? where id = ?";
	
	
	
	String somaValorMensal = "select sum(valor) valor, extract (month from datahorainicio) datahorainicio from agendamento where extract (year from datahorainicio)= ? and status='CONCLUIDO' and id_barbearia=? group by extract(month from datahorainicio)";
	

	@Override
	public void salvar(Agendamento agendamento) {
		
		String salvar = 	"""
				INSERT INTO agendamento( datahorainicio, datahoratermino, observacao, status, valor, id_cliente, id_barbeiro,nome_cliente)
				VALUES (?, ?, ?, ?, ?,?,?,?)
				""";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(salvar, new String[] { "id" });
				
				ps.setObject(1, agendamento.getDataHorarioInicio());
				ps.setObject(2, agendamento.getDataHorarioFim());
				ps.setString(3, agendamento.getObservacao());
				ps.setString(4, agendamento.getStatus().getDescricao());
				ps.setDouble(5, agendamento.getValor());
				if(agendamento.getCliente().getId() != 0) {
					ps.setInt(6, agendamento.getCliente().getId());
				}else {
					ps.setNull(6, Type.INT);
				}
				
				ps.setInt(7, agendamento.getFuncionario().getId());
				ps.setString(8, agendamento.getCliente().getNome());
				return ps;
			}
		}, keyHolder);
		
		int idAgendamento = keyHolder.getKey().intValue();
		agendamento.setId(idAgendamento);
		
		salvarAgendamentoServico(agendamento);
	}

	@Override
	public List<Agendamento> listarPorBarbeiro(int idBarbeiro) {
		
		return jdbcTemplate.query(listarPorBarbeiro,new Object[] { idBarbeiro }, new AgendamentoMapper()) ;
		
	}

	@Override
	public Agendamento buscarPorId(int idAgendamento) {
		
		return jdbcTemplate.queryForObject(buscarPorId, new Object[] { idAgendamento }, new AgendamentoMapper() );
	}

	
	
	@Override
	public void editar(Agendamento agendamento) {
		
		String editar = "UPDATE agendamento SET id=?, datahorainicio=?, datahoratermino=?, observacao=?, status=?, valor=?, id_cliente=?,id_barbeiro=?, nome_cliente=? where id = ?";


		jdbcTemplate.update(editar,agendamento.getId(),agendamento.getDataHorarioInicio(),agendamento.getDataHorarioFim(),
				agendamento.getObservacao(),agendamento.getStatus().getDescricao(),agendamento.getValor(),
				agendamento.getCliente().getId()==0?null:agendamento.getCliente().getId(),
				agendamento.getFuncionario().getId(),agendamento.getCliente().getNome(),agendamento.getId());

					
				
		String excluirAgendamentoServico = "delete from agendamento_servico where id_agendamento = ?";
		jdbcTemplate.update(excluirAgendamentoServico,agendamento.getId());
		
		salvarAgendamentoServico(agendamento);
		
		
	}
	
	private void salvarAgendamentoServico(Agendamento agendamento) {
		
		String salvarAgendamentoServicos = "INSERT INTO agendamento_servico(id_agendamento, id_servico) VALUES (?, ?)";
		
        jdbcTemplate.batchUpdate(salvarAgendamentoServicos, new BatchPreparedStatementSetter() {
			
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
	public void alterarStatus(Agendamento agendamento) {
		
		jdbcTemplate.update(alterarStatus,agendamento.getStatus().getDescricao(),agendamento.getId());
		
	}

	@Override
	public List<Agendamento> buscarPorData(LocalDate data,int idBarbeiro){
		
		if(idBarbeiro!=0) {
			String buscarPorData = "select a.id id_agendamento, a.datahorainicio, a.datahoratermino,a.observacao,a.status,a.valor,a.id_barbeiro," + 
					"c.id id_cliente," + 
					"a.nome_cliente" + 
					" from agendamento a" + 
					" left join cliente c on a.id_cliente = c.id" + 
					" inner join funcionario f on a.id_barbeiro = f.id" + 
					" where to_char(a.datahorainicio,'YYYY-MM-DD') = ? and a.status NOT IN('CANCELADO') and a.id_barbeiro = ?";
			
			return jdbcTemplate.query(buscarPorData,new Object[] {data.toString(),idBarbeiro}, new AgendamentoMapper());
		}else {
			//pegar os agendamentos para notificar 
			String buscarPorData = "select a.id id_agendamento, a.datahorainicio, a.datahoratermino,a.observacao,a.status,a.valor,a.id_barbeiro," + 
					"c.id id_cliente," + 
					"a.nome_cliente" + 
					" from agendamento a" + 
					" left join cliente c on a.id_cliente = c.id" + 
					" inner join funcionario f on a.id_barbeiro = f.id" + 
					" where to_char(a.datahorainicio,'YYYY-MM-DD') = ? and a.status = 'AGENDADO' and notificado != true and id_cliente is not null";
			
			return jdbcTemplate.query(buscarPorData,new Object[] {data.toString()}, new AgendamentoMapper());
		}
		
		
	}
	
	@Override
	public List<Relatorio>somaValorMensal(int idBarbearia, LocalDate data){
		return jdbcTemplate.query(somaValorMensal, new Object[] { data.getYear(),idBarbearia}, (rs, rowNum)-> new Relatorio(rs.getDouble("valor"), rs.getInt("datahorainicio")));
				
	}

	@Override
	public void alterarNotificado(int idAgendamento) {
		
		var alterarNotificado = "update agendamento set notificado = true where id =?";
		jdbcTemplate.update(alterarNotificado,idAgendamento);
		
	}
	
	
}