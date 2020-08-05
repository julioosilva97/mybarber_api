package com.mybarber.api.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Usuario;

@Repository
public class UsuarioPerfilDAOImpl implements UsuarioPerfilDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String salvar = "insert into usuario_perfil values (?,?)";
	
	String excluir = "delete from usuario_perfil where id_usuario =?";
	
	String alterar = "update usuario_perfil set id_perfil = ? where id_usuario= ?";
	
	@Override
	public void salvar(Usuario usuario) {
		jdbcTemplate.update(salvar,usuario.getId(),usuario.getPerfil().getId());
	}


	@Override
	public void excluir(Usuario usuario) {
		
		jdbcTemplate.update(excluir, usuario.getId());	
		
	}


	@Override
	public void editar(Usuario usuario) {
		
        jdbcTemplate.update(alterar, usuario.getPerfil().getId(),usuario.getId());

		
	}

}
