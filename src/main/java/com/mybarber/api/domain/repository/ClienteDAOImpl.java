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
import com.mybarber.api.domain.entity.Cliente;
import com.mybarber.api.domain.entity.Endereco;
import com.mybarber.api.domain.entity.Usuario;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	String salvar = "INSERT INTO cliente(nome, telefone, email, data_nascimento) VALUES (?, ?, ?, ?)";
	
	String buscarPorid = "select * from cliente where id = ?";
	
	String editar = """
			UPDATE cliente SET  nome=?, telefone=?, email=?, data_nascimento=? WHERE id = ?
			""";
	
	String listar = "select c.* from cliente c inner join cliente_barbearia cb on cb.id_cliente = c.id where cb.id_barbearia = ?";
	
	String excluir = "delete from cliente where id = ?";
	
	
	
	@Override
	public void cadastrar(Cliente cliente) {
		
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(salvar, new String[] { "id" });
					ps.setString(1, cliente.getNome());
					ps.setString(2, cliente.getTelefone());
					ps.setString(3, cliente.getEmail());
					ps.setObject(4, cliente.getDataNascimento());
					
					return ps;
				}
			}, keyHolder);
			
			int idCliente = keyHolder.getKey().intValue();
			cliente.setId(idCliente);
		}catch (Exception e) {
			throw e ;
		}
		
	}

	@Override
	public Cliente buscarPorid(int id) {
		return jdbcTemplate.queryForObject(buscarPorid, new Object[] { id }, (rs, rowNum) ->
		new Cliente(rs.getInt("id"),rs.getString("nome"),rs.getString("telefone"),rs.getString("email"),rs.getDate("data_nascimento").toLocalDate() ,
				new Endereco(),
				new Usuario()
				));
	}

	@Override
	public void editar(Cliente cliente) {
		
		jdbcTemplate.update(editar, cliente.getNome(), cliente.getTelefone(),cliente.getEmail(), cliente.getDataNascimento(),cliente.getId());	
		
	}

	@Override
	public void excluir(int id) {
		jdbcTemplate.update(excluir, id);	
		
	}

	@Override
	public List<Cliente> listar(Barbearia barbearia) {
		return jdbcTemplate.query(listar,  new Object[] { barbearia.getId() },
				(rs, rowNum) ->
		new Cliente(rs.getInt("id"), 
				rs.getString("nome"), 
				rs.getString("telefone"),
				rs.getString("email"),
				rs.getDate("data_nascimento").toLocalDate(),
				new Endereco(),
				new Usuario()
				) ) ;
	}

	@Override
	public boolean verificarEmail(String email) {
		
		String verificarEmail = "SELECT EXISTS(SELECT FROM cliente WHERE email = ?)";
		
		
		return jdbcTemplate.queryForObject( verificarEmail , new Object[] { email }, Boolean.class);
	}

}
