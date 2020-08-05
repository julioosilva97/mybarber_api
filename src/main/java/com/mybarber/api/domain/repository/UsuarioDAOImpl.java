package com.mybarber.api.domain.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Perfil;
import com.mybarber.api.domain.entity.Usuario;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String salvar = "insert into usuario(login,senha,ativo) values(?,?,?)";

	String buscar = """
			select * from usuario u inner join usuario_perfil up on up.id_usuario = u.id 
            inner join perfil p on up.id_perfil = p.id 
            where u.id = ?
			""";

	String alterar = "update usuario set login = ?, senha = ? where id =?";
	String excluir = "delete from usuario where id =?";
	String buscarportoken = "SELECT * FROM usuario WHERE reset_token = ?";
	String alterarsenha = "update usuario set senha =?, ativo=? where id =?";

	String buscarPorLogin = "select u.id, u.login,u.ativo" + " from usuario u" + " where u.login = ?";


	@Override
	public void salvar(Usuario usuario) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(salvar, new String[] { "id" });
				ps.setString(1, usuario.getLogin());
				ps.setString(2, usuario.getSenha());
				ps.setBoolean(3, false);
				return ps;
			}
		}, keyHolder);

		int idUsuario = keyHolder.getKey().intValue();
		usuario.setId(idUsuario);
	}

	@Override
	public Usuario buscar(int id) {
		return jdbcTemplate.queryForObject(buscar, new Object[] { id },
				(rs, rowNum) -> new Usuario(rs.getInt("id"), rs.getString("login"), rs.getString("senha"),
						rs.getBoolean("ativo"), new Perfil(rs.getInt("id_perfil"), rs.getString("descricao"))));
	}

	@Override
	public void alterar(Usuario usuario) {

		jdbcTemplate.update(alterar, usuario.getLogin(), usuario.getSenha(),usuario.getId());
	}

	@Override
	public void excluir(Usuario usuario) {
		// trazer id
		jdbcTemplate.update(excluir, usuario.getId());
	}

	@Override
	public void alterarSenha(Usuario usuario) {

		jdbcTemplate.update(alterarsenha, usuario.getSenha(), true, usuario.getId());
	}

	@Override
	public Usuario buscarPorLogin(String login) {

		try {
			return jdbcTemplate.queryForObject(buscarPorLogin, new Object[] { login },
					(rs, rowNum) -> new Usuario(rs.getInt("id"), rs.getString("login"), rs.getBoolean("ativo")));

		} catch (Exception e) {
			return null;
		}

	}

}
