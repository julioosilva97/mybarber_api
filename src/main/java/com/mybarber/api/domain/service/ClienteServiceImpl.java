package com.mybarber.api.domain.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.entity.Cliente;
import com.mybarber.api.domain.exception.NegocioException;
import com.mybarber.api.domain.repository.ClienteDAO;
import com.mybarber.api.domain.repository.UsuarioDAO;

@Service @Transactional
public class ClienteServiceImpl implements ClienteService {

	
	
	@Autowired
	ClienteDAO clienteDAO;
	
	@Autowired
	UsuarioDAO usuarioDAO;
	

	
	@Override
	public void cadastrar(Cliente cliente,int idBarbearia) {
		
		if (!usuarioDAO.verificarLogin(cliente.getUsuario().getLogin())) {
		cliente.getUsuario().getPerfil().setId(4);
		usuarioDAO.salvar(cliente.getUsuario());
		clienteDAO.cadastrar(cliente,idBarbearia);
		
		//enviar email para o cliente se cadastrar 
		
		} else {
            throw new NegocioException("Login já existente.");
        }
		
	}

	@Override
	public Cliente buscarPorid(int id) {
		
		return clienteDAO.buscarPorid(id);
	}

	@Override
	public void editar(Cliente cliente) {
		
		if(cliente.getId()!=0) {
			
			if(cliente.getUsuario().getId()!=0) {
				
				var usuarioEdicao = cliente.getUsuario();
    			
    			var usuarioAntigoLogin = usuarioDAO.buscarPorLogin(usuarioEdicao.getLogin());
    			
    			if(usuarioAntigoLogin == null || usuarioAntigoLogin.getId() == usuarioEdicao.getId()) {
    				
    				var usuarioAntigoEmail = usuarioDAO.buscarPorEmail(usuarioEdicao.getEmail());
    				
    				if(usuarioAntigoEmail == null || usuarioAntigoEmail.getId() == usuarioEdicao.getId()) {
    					cliente.getUsuario().getPerfil().setId(4);
    					usuarioDAO.alterar(cliente.getUsuario());
    					clienteDAO.editar(cliente);
    					
    				}else {
    					throw new NegocioException("Já existe um usuário com email : "+usuarioEdicao.getEmail());
    				}
    				
    			}else {
    				throw new NegocioException("Já existe um usuário com login : "+usuarioEdicao.getLogin());
    			}
    			
			}else {
				
				throw new NegocioException("Usuário sem id");
			}
			
			
		}else {
			throw new NegocioException("Cliente sem id");
		}
		
		
		
		//clienteDAO.editar(cliente);
		
	}

	@Override
	public void excluir(int id) {
		
		
		clienteDAO.excluir(id);
		
	}

	@Override
	public List<Cliente> listar(Barbearia barbearia) {
		
		return clienteDAO.listar(barbearia);
	}

}
