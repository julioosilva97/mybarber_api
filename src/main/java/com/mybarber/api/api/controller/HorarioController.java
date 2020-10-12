package com.mybarber.api.api.controller;



import com.mybarber.api.domain.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/horarios")
public class HorarioController {

    @Autowired
    FuncionarioService service;

    

}
