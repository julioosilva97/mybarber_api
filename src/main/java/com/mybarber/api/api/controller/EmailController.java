package com.mybarber.api.api.controller;

import com.mybarber.api.api.facade.FuncionarioFacade;
import com.mybarber.api.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("emails")
public class EmailController {

    @Autowired
    FuncionarioService service;

    @GetMapping("verificarEmail/{email}")
    public ResponseEntity<Boolean> verificarUsuario(@PathVariable("email") String email) {

        return new ResponseEntity<Boolean>(service.verificarEmail(email), HttpStatus.OK);
    }

}
