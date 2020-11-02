package com.mybarber.api.domain.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mybarber.api.domain.entity.Usuario;



@Service
public class RelatorioService {
	
	
	

	public static List<Usuario> getStudents() {

        List<Usuario> usuarios = new ArrayList<Usuario>();
       

        Usuario usuario = new Usuario(1,"victor");

        //adiciona um carro a nossa lista
        usuarios.add(usuario);
        
       return usuarios;
	
}
}
