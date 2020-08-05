package com.mybarber.api.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Endereco;



@Repository
public class BarbeariaDAOImpl implements BarbeariaDAO {

	@Autowired JdbcTemplate jdbcTemplate;
	
	String salvar = "insert into barbearia (nome,descricao,id_endereco) values(?,?,?)";
	String salvarSemDescricao = "insert into barbearia(nome,id_endereco) values(?,?)";
	String listar = "select b.id, b.nome, b.descricao,"
			+ "e.id id_endereco, e.numero ,e.cep, e.logradouro, e.bairro, e.cidade, e.uf "
			+ "from barbearia b inner join endereco e on b.id_endereco = e.id ";
	String buscarPorId = "select b.id, b.nome, b.descricao,"
			+ "e.id id_endereco, e.numero ,e.cep, e.logradouro, e.bairro, e.cidade, e.uf "
			+ "from barbearia b inner join endereco e on b.id_endereco = e.id"
			+ " where b.id =?";
	String deletar = "delete from barbearia where id= ?";
	String update = "update barbearia set nome=?, descricao = ? where id = ?";
	

	@Override
	public Barbearia salvar(Barbearia barbearia) {
	
		KeyHolder holder = new GeneratedKeyHolder();

		if(barbearia.getDescricao()!=null) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(salvar, new String[] { "id" });
				ps.setString(1, barbearia.getNome());
				ps.setString(2, barbearia.getDescricao());
				ps.setInt(3, barbearia.getEndereco().getId());
				return ps;
			}
		},holder);
		}else {
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(salvarSemDescricao, new String[] { "id" });
					ps.setString(1, barbearia.getNome());
					ps.setInt(2, barbearia.getEndereco().getId());
					return ps;
				}
			},holder);
		
		}
		int idBarbearia  = holder.getKey().intValue();
		barbearia.setId(idBarbearia);
		return barbearia;
	}

	@Override
	public List<Barbearia> listar() {
		
		Endereco endereco = new Endereco();
		return this.jdbcTemplate.query(listar,(rs,rowNum)-> new Barbearia(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"),endereco));
	}

	

	@Override
	public void alterar(Barbearia barbearia) {
		this.jdbcTemplate.update(update,barbearia.getNome(),barbearia.getDescricao(),barbearia.getId());
	}

	@Override
	public void excluir(int id) {
		this.jdbcTemplate.update(deletar,id);
	}

	@Override
	public Barbearia buscarPorId(int id) {
		return this.jdbcTemplate.queryForObject(buscarPorId, new Object[] {id}, (rs,rowNum) -> new Barbearia(rs.getInt("id"),rs.getString("nome"),rs.getString("descricao"),
				new Endereco(rs.getInt("id_endereco"),rs.getString("logradouro"),rs.getString("bairro"),rs.getInt("numero"),rs.getString("cep"),rs.getString("cidade"),rs.getString("uf"))));
	}
	
}
