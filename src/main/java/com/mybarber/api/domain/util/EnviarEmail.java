package com.mybarber.api.domain.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import javax.mail.internet.MimeMessage;

import com.mybarber.api.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

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
    
    @Autowired
    GerarPdf gerarPDF;

	
	public void ativarConta(Pessoa pessoa,TokenDeVerificacao token) {
		
			var email  = gerarEmail(pessoa,token);
			email.setAssunto("Ativação de conta MyBarber");
			email.getMap().put("resetUrl","http://localhost:8081/ativar-conta?token=" + token.getToken()+"&ativacao");
			enviarEmail(email,"email/ativar-conta.html");
		
	}


	public void resetarSenha(Pessoa pessoa,TokenDeVerificacao token)  {
		

		var email  = gerarEmail(pessoa,token);
		email.setAssunto("Redefinição de senha MyBarber");
		email.getMap().put("resetUrl","http://localhost:8081/resetar-senha?token=" + token.getToken());
		
		enviarEmail(email,"email/redefinir-senha.html");
		//enviarRelatorio(pessoa);
	}
	
	public void notificarAgendamento(Agendamento agendamento) {
		
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");
		
		var email = gerarEmail(agendamento.getCliente(),null);
		email.setAssunto("Notificação de agendamento");
		email.getMap().put("inicio",agendamento.getDataHorarioInicio().format(formatterTime).toString());
		email.getMap().put("termino",agendamento.getDataHorarioFim().format(formatterTime).toString());
		email.getMap().put("dia",agendamento.getDataHorarioInicio().format(formatterDate).toString());
		enviarEmail(email,"email/notificar-agendamento.html");
	}
	
	
	
	private  Email gerarEmail(Pessoa pessoa,TokenDeVerificacao token) {
		
		try {
			var email = new Email();
			email.setDe("MyBarber <mybarber.btech@gmail.com>");
			email.setPara(pessoa.getUsuario().getEmail());
			
			Map<String, Object> map = new HashMap<>();
			if(token!=null) {
				map.put("token", token.getToken());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:m");
				map.put("validadeToken", "Token válido até " + token.getDataHoraExpiracao().format(formatter)+".");
			}
			
			map.put("pessoa", pessoa);
			map.put("signature", "https://com.mybarber.api.br");

			email.setMap(map);
			return email;
		}catch (Exception e) {
			throw new NegocioException(e.getMessage());
		}
		
	}
	
	
	public void enviarRelatorio(Pessoa pessoa, int idBarbearia) {
		  
		   var pdf = gerarPDF.generatePdf(idBarbearia);
		   
		   System.out.println(pdf);
		   
		   var email = gerarEmail(pessoa, null);
		   email.setAssunto("Relatório mensal");
		   email.setAnexo(pdf);
		   enviarEmail(email, "email/relatorio");
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
		        
		        if(email.getAnexo() !=null) {
		        
		           helper.addAttachment("relatorio.pdf", email.getAnexo());
		        }
		    
		        emailSender.send(message);
				
			}catch (Exception e) {
				 throw new RuntimeException(e);
			}	
		}
}
