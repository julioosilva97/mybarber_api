package com.mybarber.api.api.controller;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.entity.Usuario;
import com.mybarber.api.domain.service.BarbeariaService;
import com.mybarber.api.domain.service.FuncionarioService;
import com.mybarber.api.domain.service.UsuarioService;

@Controller
public class HtmlController {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private BarbeariaService barbeariaService;
	
	@GetMapping("/")
	public String index(HttpServletRequest request,Principal principal){
        
		String login = principal.getName(); //pega o login
		Usuario usuario = usuarioService.buscarPorLogin(login);//busca pelo nome
		
		
		//verificar se existe algum funcionario relacionado ao id da pessoa
		Funcionario funcionario = funcionarioService.buscarPorIdUsuario(usuario.getId());
		if(funcionario != null){
			request.getSession().setAttribute("barbearia", barbeariaService.buscarPorId(funcionario.getBarbearia().getId()));
			request.getSession().setAttribute("funcionario", funcionario);
		}
		
		
		return "fragments/dashboard";
	}
	
	@GetMapping("/senha-redefinida")
	public String  senhaRedefinida(ModelMap model){
		
		
		return "login/login";
		
	}
	
	
	@GetMapping("/servicos")
	public String servicos(){
		return "servico/servico";
	}
	
	@GetMapping("/funcionarios")
	public String funcionario() {
		return "funcionario/funcionario";
	}

	@GetMapping("/login")
	public String login() {
		
		return "login/login";
	}
	
	
	
	@GetMapping("/agenda")
	public String agenda() {
		return "agendamento/agenda";
	}
	
	@GetMapping("/usuarios/reset")
	public String senha() {
		return "senha/reset-password";
	}
	
	@GetMapping("/clientes")
	public String clientes() {
		return "cliente/cliente";
	}
	
	@GetMapping("/barbearia")
	public String barbearia() {
		return "barbearia/barbearia";
	}
	
	@GetMapping("registro")
	public String registro() {
		return"registro/registro";
	}
	
	@GetMapping("relatorioMensal")
	public String mensal() {
		return "relatorio/barGraph";
	}
	
	@GetMapping("teste")
	public String teste() {
		return "email/email-template";
	}
	
	@GetMapping("resetar-senha")
	public String resetarSenha() {
		
		
		return "senha/resetar-senha";
	}
	
	@GetMapping("ativar-conta")
	public String ativarConta() {
		return "senha/resetar-senha";
	}
	
}
