package com.mybarber.api.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Servico;
import com.mybarber.api.domain.rowmapper.ServicoMapper;


@Repository
public class ServicoDAOImpl implements ServicoDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String selectall = "select * from servico where id_barbearia = ?";
	String select = "select * from servico where id = ?";
	String save = "insert into servico(descricao,valor,tempo,id_barbearia) values(?,?,?,?)";
	String delete = "delete from servico where id=?";
	String update = "update servico set descricao = ?, valor =?,tempo=? where id = ?";

	@Override
	public List<Servico> listar(int id) {
		return jdbcTemplate.query(selectall, new Object[] { id }, new ServicoMapper());

	}

	@Override
	public void salvar(Servico servico) {
		this.jdbcTemplate.update(save, servico.getDescricao(), servico.getValor(),
				java.sql.Time.valueOf(servico.getTempo()),servico.getBarbearia().getId());
	}

	@Override
	public void excluir(int id) {
		this.jdbcTemplate.update(delete, id);
	}

	@Override
	public void atualizar(Servico servico) {
		this.jdbcTemplate.update(update, servico.getDescricao(), servico.getValor(),
				java.sql.Time.valueOf(servico.getTempo()), servico.getId());

	}

	@Override
	public Servico buscarPorId(int id) {
		return this.jdbcTemplate.queryForObject(select, new Object[] { id }, (rs, rowNum) -> new Servico(rs.getInt("id"),
				rs.getString("descricao"), rs.getFloat("valor"), rs.getTime("tempo").toLocalTime()));

	}

}
