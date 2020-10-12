package com.mybarber.api.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Promocao;
import com.mybarber.api.domain.entity.Servico;

@Repository
public class PromocaoDAOImpl implements PromocaoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String save = "insert into promocao (dataInicio, dataFim, descricao,status,valor, id_servico) values (?, ?, ?,?,?,?) ";
	private String delete = "delete from promocao where id = ?";
	private String update = "update promocao  set dataInicio = ?, dataFim = ?, descricao= ?, valor=? where id_servico =?";
    private String status = "select status from promocao where id_servico=?";
  
    
	@Override
	public void salvar(Promocao promocao) {

		jdbcTemplate.update(save,promocao.getDataInicio(), promocao.getDataFim(),promocao.getDescricao(),true,promocao.getValor(), promocao.getServico().getId());
	}

	@Override
	public void editar(Promocao promocao) {
		
		jdbcTemplate.update(update,promocao.getDataInicio(), promocao.getDataFim(), promocao.getDescricao(), promocao.getValor(), promocao.getServico().getId());
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Promocao status(int idServico) {
	
	   return jdbcTemplate.queryForObject(status, new Object[] {idServico}, (rs, rowNum) -> new Promocao(rs.getBoolean("status")));	
	}

	@Override
	public Promocao buscarPorIdServico(int idServico) {
		String buscarPorId = "select * from promocao where id_servico = ?";
		try {
		return jdbcTemplate.queryForObject(buscarPorId, new Object[] {idServico}, 
				(rs, rowNum) -> new Promocao(rs.getInt("id"),rs.getDate("dataInicio").toLocalDate(), rs.getDate("dataFim").toLocalDate(),rs.getString("descricao"),rs.getBoolean("status"),rs.getFloat("valor"), new Servico(rs.getInt("id_servico"))));

		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void inativarPromocao(int idPromocao) {
		 String inativarPromocao = "update promocao set status =? where id = ?";
		jdbcTemplate.update(inativarPromocao, false, idPromocao);
	}

	@Override
	public List <Promocao> buscarPromocoesAtivas() {
		 String ativas = "select * from promocao where status = ?";
		return jdbcTemplate.query(ativas, new Object[] {true},
				(rs, rowNum) -> new Promocao(rs.getInt("id"),rs.getDate("dataInicio").toLocalDate(), rs.getDate("dataFim").toLocalDate(),rs.getString("descricao"),rs.getBoolean("status"),rs.getFloat("valor")));
	}

	

}
