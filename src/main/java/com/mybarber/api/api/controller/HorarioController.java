package com.mybarber.api.api.controller;


import com.mybarber.api.api.dto.funcionario.HorarioAtendimentoDTOInput;
import com.mybarber.api.api.facade.FuncionarioFacade;
import com.mybarber.api.domain.entity.HorarioAtendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("horarios")
public class HorarioController {

    @Autowired
    FuncionarioFacade facade;

    @PostMapping("/horarioAtendimento")
    public ResponseEntity<Void> defirnirHorarioAtendimento(@RequestBody List<HorarioAtendimentoDTOInput> horarios) {

        facade.salvarHorarioAtendimento(horarios);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/buscarHorarioAtendimento/{id}")
    public ResponseEntity<List<HorarioAtendimento>> buscarHorarioAtendimentoPorFuncionario(@PathVariable("id") int id) {

        return new ResponseEntity<List<HorarioAtendimento>>(facade.buscarHorarioAtendimentoPorFuncionario(id),
                HttpStatus.OK);

    }


}
