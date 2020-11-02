package com.mybarber.api.domain.util;

import com.lowagie.text.DocumentException;

import com.mybarber.api.domain.service.RelatorioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class GerarPdf {
	
	
	
	@Autowired
	private EnviarEmail enviar;

	private static final String PDF_RESOURCES = "/pdf-resources/";
  
    private SpringTemplateEngine templateEngine;

    @Autowired
    public GerarPdf( SpringTemplateEngine templateEngine) {
    
        this.templateEngine = templateEngine;
    }

    public  File generatePdf()  {
        Context context = getContext();
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html)  {
    	try {
        File file = File.createTempFile("students", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    	}catch(Exception e) {
    		return null;
    	}
    }

   private Context getContext() {
        Context context = new Context();
        context.setVariable("students", RelatorioService.getStudents());
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("relatorio", context);
    }
    
}
