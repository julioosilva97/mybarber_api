package com.mybarber.api.domain.util;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import javax.mail.internet.MimeMessage;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.mybarber.api.domain.entity.Email;
import com.mybarber.api.domain.entity.Pessoa;
import com.mybarber.api.domain.entity.TokenDeVerificacao;
import com.mybarber.api.domain.exception.NegocioException;
import com.mybarber.api.domain.repository.TokenDeVerificacaoDAO;

@Component
public class EnviarEmail {

	@Autowired
	TokenDeVerificacaoDAO tokenRepository;
	
	@Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

	
	public void ativarConta(Pessoa pessoa,TokenDeVerificacao token) {
		
			var email  = gerarEmail(pessoa,token);
			email.setAssunto("Ativa��o de conta MyBarber");
			email.getMap().put("resetUrl","http://localhost:8080/ativar-conta?token=" + token.getToken()+"&ativacao");
			enviarEmail(email,"email/ativar-conta.html");
		
	}


	public  void resetarSenha(Pessoa pessoa,TokenDeVerificacao token) {
		

		var email  = gerarEmail(pessoa,token);
		email.setAssunto("Redefini��o de senha MyBarber");
		email.getMap().put("resetUrl","http://localhost:8080/resetar-senha?token=" + token.getToken());
		
		enviarEmail(email,"email/redefinir-senha.html");
	}
	
	
	
	private  Email gerarEmail(Pessoa pessoa,TokenDeVerificacao token) {
		
		try {
			var email = new Email();
			email.setDe("MyBarber <mybarber.btech@gmail.com>");
			//email.setPara(pessoa.getEmail()); passar email do usuario
			
			Map<String, Object> map = new HashMap<>();
			map.put("token", token.getToken());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:m");
			map.put("validadeToken", "Token v�lido at� " + token.getDataHoraExpiracao().format(formatter)+".");
			map.put("pessoa", pessoa);
			map.put("signature", "https://com.mybarber.api.br");
			
			
			
			System.out.println(map);
			
			email.setMap(map);
			return email;
		}catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	private  void enviarEmail(Email email,String template) {
		
		try {
			MimeMessage message = emailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,
	        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
	        StandardCharsets.UTF_8.name());
	        
	        Context context = new Context();
	        context.setVariables(email.getMap());
	        
	        var html = templateEngine.process(template, context);
	        
	        helper.setTo(email.getPara());
	        helper.setText(html, true);
	        helper.setSubject(email.getAssunto());
	        helper.setFrom(email.getDe());

	        emailSender.send(message);
			
		}catch (Exception e) {
			 throw new RuntimeException(e);
		}	
	}
}
