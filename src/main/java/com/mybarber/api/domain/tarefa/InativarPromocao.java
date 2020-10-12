package com.mybarber.api.domain.tarefa;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mybarber.api.domain.repository.PromocaoDAO;

@Component
public class InativarPromocao {
	
	@Autowired
	PromocaoDAO promocaoDAO;
	
	 LocalDate dataFim;
	
	
	private static final String TIME_ZONE = "America/Sao_Paulo";

	
	@Scheduled(cron = "0 21 20 * * *",zone = TIME_ZONE)
	@Transactional
	private void inativarPromocoesVencidas() {
	   var promocoes = promocaoDAO.buscarPromocoesAtivas();
	   
	   
	   promocoes.forEach(promocao -> {
		   
		   if(promocao.getDataFim().isBefore(LocalDate.now())) {
		   var idPromocao = promocao.getId();
		   promocaoDAO.inativarPromocao(idPromocao);
		   System.out.println("foi");
		   }else {
			   System.out.println("Nada");
		   }
	   });
	
	   
	}

	
}
