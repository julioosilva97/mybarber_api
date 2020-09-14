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

    @PostMapping("/horarioAtendimento")
    public ResponseEntity<Void> defirnirHorarioAtendimento(@RequestBody List<HorarioAtendimentoDTOInput> horarios) {

        //facade.salvarHorarioAtendimento(horarios);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/buscarHorarioAtendimento/{id}")
    public ResponseEntity<List<HorarioAtendimento>> buscarHorarioAtendimentoPorFuncionario(@PathVariable("id") int id) {

        /*return new ResponseEntity<List<HorarioAtendimento>>(service.buscarHorarioAtendimentoPorFuncionario(id),
                HttpStatus.OK);*/

        return  null;

    }


}
