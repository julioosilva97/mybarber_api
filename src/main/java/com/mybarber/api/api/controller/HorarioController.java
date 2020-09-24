package com.mybarber.api.api.controller;


import com.mybarber.api.api.dto.funcionario.HorarioAtendimentoDTOInput;
import com.mybarber.api.domain.entity.HorarioAtendimento;
import com.mybarber.api.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/horarios")
public class HorarioController {

    @Autowired
    FuncionarioService service;

    

}
