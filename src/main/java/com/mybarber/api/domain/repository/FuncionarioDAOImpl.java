package com.mybarber.api.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.rowmapper.FuncionarioMapper;
import com.mybarber.api.domain.util.Cargo;



@Repository
public class FuncionarioDAOImpl implements FuncionarioDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	String salvar = """
			INSERT INTO funcionario( nome, sobrenome, telefone, email, data_nascimento, id_endereco, cargo, id_barbearia, id_usuario)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";



	String listar = """
			select f.id id_funcionario,f.cargo, f.nome nome, f.telefone, f.email, f.data_nascimento,
			 e.id id_endereco,e.logradouro,e.bairro,e.numero,e.cep, e.cidade, e.uf,
			 u.id id_usuario, u.login, u.ativo, per.id id_perfil, per.descricao descricao_perfil,
			 b.id id_barbearia
			 from funcionario f 
			 left join endereco e on f.id_endereco = e.id 
			 inner join usuario u on f.id_usuario = u.id
	         inner join usuario_perfil up on up.id_usuario = u.id
	         inner join perfil per on per.id = up.id_perfil
	         inner join barbearia b on f.id_barbearia = b.id
	         where b.id = ?
			""";

	String buscar ="""
			select f.id id_funcionario,f.cargo, f.nome nome, f.telefone, f.email, f.data_nascimento,
			 e.id id_endereco,e.logradouro,e.bairro,e.numero,e.cep, e.cidade, e.uf,
			 u.id id_usuario, u.login, u.ativo, per.id id_perfil, per.descricao descricao_perfil,
			 b.id id_barbearia
			 from funcionario f 
			 left join endereco e on f.id_endereco = e.id 
			 inner join usuario u on f.id_usuario = u.id
	         inner join usuario_perfil up on up.id_usuario = u.id
	         inner join perfil per on per.id = up.id_perfil
	         inner join barbearia b on f.id_barbearia = b.id
	         where f.id = ?
			""";


	String excluir = "delete from funcionario where id = ?";

	String listarPorCargo = """
			select f.id id_funcionario,f.cargo, f.nome nome, f.telefone, f.email, f.data_nascimento,
			 e.id id_endereco,e.logradouro,e.bairro,e.numero,e.cep, e.cidade, e.uf,
			 u.id id_usuario, u.login, u.ativo, per.id id_perfil, per.descricao descricao_perfil,
			 b.id id_barbearia
			 from funcionario f 
			 inner join endereco e on f.id_endereco = e.id 
			 inner join usuario u on f.id_usuario = u.id
	         inner join usuario_perfil up on up.id_usuario = u.id
	         inner join perfil per on per.id = up.id_perfil
	         left join barbearia b on f.id_barbearia = b.id
	         where f.cargo = ? and f.id_barbearia = ? 
			""";

	String buscarPorIdUsuario = """
			select f.id id_funcionario,f.cargo, f.nome nome, f.telefone, f.email, f.data_nascimento,
			 e.id id_endereco,e.logradouro,e.bairro,e.numero,e.cep, e.cidade, e.uf,
			 u.id id_usuario, u.login, u.ativo, per.id id_perfil, per.descricao descricao_perfil,
			 b.id id_barbearia
			 from funcionario f 
			 left join endereco e on f.id_endereco = e.id 
			 inner join usuario u on f.id_usuario = u.id
	         inner join usuario_perfil up on up.id_usuario = u.id
	         inner join perfil per on per.id = up.id_perfil
	         left join barbearia b on f.id_barbearia = b.id
	         where f.id_usuario= ? 
			""";

	String verificarEmail = "SELECT EXISTS(SELECT FROM funcionario WHERE email = ?)";



	@Override
	public void salvar(Funcionario funcionario) {

		try {
			jdbcTemplate.update(salvar,funcionario.getNome(),
					funcionario.getSobrenome()!=null?funcionario.getSobrenome() : null,
					funcionario.getTelefone()!=null?funcionario.getTelefone() : null ,
					funcionario.getEmail(),
					funcionario.getDataNascimento() !=null?funcionario.getDataNascimento(): null,
					funcionario.getEndereco()!=null?funcionario.getEndereco().getId(): null,
					funcionario.getCargo().getDescricao(),funcionario.getBarbearia().getId(),funcionario.getUsuario().getId());

		}catch (Exception e) {
			new Exception("Erro ao salvar ");
		}

	}

	@Override
	public List<Funcionario> listar(int id) {

			return jdbcTemplate.query(listar, new Object[] { id },new FuncionarioMapper()) ;

	}


	@Override
	public Funcionario buscar(int id) {

		try {

		return jdbcTemplate.queryForObject(buscar, new Object[] { id },new FuncionarioMapper());
		}catch(Exception ex) {
			return null;
		}
	}

	@Override
	public void alterar(Funcionario funcionario) {

		String alterar = """
				UPDATE funcionario SET nome=?, sobrenome=?, telefone=?, email=?, data_nascimento=?, id_endereco=?, cargo=?
				WHERE id = ?
				""";

		jdbcTemplate.update(alterar,funcionario.getNome(),
				funcionario.getSobrenome()!=null?funcionario.getSobrenome() : null,
				funcionario.getTelefone()!=null?funcionario.getTelefone() : null ,
				funcionario.getEmail(),
				funcionario.getDataNascimento() !=null?funcionario.getDataNascimento(): null,
				funcionario.getEndereco()!=null?funcionario.getEndereco().getId(): null,
				funcionario.getCargo().getDescricao(),
				funcionario.getId());

	}

	@Override
	public void excluir(Funcionario funcionario) {

		jdbcTemplate.update(excluir, funcionario.getId());
	}

	@Override
	public List<Funcionario> listarPorCargo(Cargo cargo, int id_barbearia) {

		return jdbcTemplate.query(listarPorCargo,new Object[] { cargo.getDescricao(),id_barbearia }, new FuncionarioMapper()) ;

	}



	@Override
	public Funcionario buscarPorIdUsuario(int idUsuario) {

		return jdbcTemplate.queryForObject(buscarPorIdUsuario,new Object[] { idUsuario }, new FuncionarioMapper()) ;
	}

	@Override
	public boolean verificarEmail(String email) {

	     return jdbcTemplate.queryForObject( verificarEmail , new Object[] { email }, Boolean.class);

	}

	@Override
	public Funcionario buscarPorEmail(String email) {

		var buscarPorIdUsuario = """
				select f.id id_funcionario,f.cargo, f.nome nome, f.telefone, f.email, f.data_nascimento,
				 e.id id_endereco,e.logradouro,e.bairro,e.numero,e.cep, e.cidade, e.uf,
				 u.id id_usuario, u.login, u.ativo, per.id id_perfil, per.descricao descricao_perfil,
				 b.id id_barbearia
				 from funcionario f 
				 inner join endereco e on f.id_endereco = e.id 
				 inner join usuario u on f.id_usuario = u.id
		         inner join usuario_perfil up on up.id_usuario = u.id
		         inner join perfil per on per.id = up.id_perfil
		         left join barbearia b on f.id_barbearia = b.id
		         where f.email= ? 
				""";


		return jdbcTemplate.queryForObject(buscarPorIdUsuario,new Object[] { email }, new FuncionarioMapper()) ;
	}
}
