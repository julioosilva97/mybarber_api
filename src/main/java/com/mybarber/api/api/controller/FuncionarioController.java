package com.mybarber.api.api.controller;

import java.util.*;
import java.util.stream.Collectors;


import javax.validation.Valid;

import com.mybarber.api.api.dto.funcionario.FuncionarioInput;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.api.util.ConverterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.mybarber.api.api.dto.funcionario.FuncionarioDTO;
import com.mybarber.api.domain.entity.Funcionario;
import com.mybarber.api.domain.service.FuncionarioService;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody FuncionarioInput funcionarioDto) {

        var funcionario = (Funcionario) ConverterDTO.toDoMain(funcionarioDto, Funcionario.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("funcionario", funcionario);
        map.put("primeiroFuncionario", funcionarioDto.getPrimeiroFuncionario());

        service.salvar(map);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> iniciarEdicao(@PathVariable("id") int id) {

        var funcionario = service.buscar(id);
        var funcionarioDTO = (FuncionarioDTO) ConverterDTO.toDTO(funcionario, FuncionarioDTO.class);

        return new ResponseEntity<FuncionarioDTO>(funcionarioDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editar(@Valid @RequestBody FuncionarioInput funcionarioDto) {

        var funcionario = (Funcionario) ConverterDTO.toDoMain(funcionarioDto, Funcionario.class);

        service.alterar(funcionario);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") int id) {

        service.excluir(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @GetMapping("listarPorCargo/{cargo}/{idBarbearia}")
    public ResponseEntity<List<FuncionarioDTO>> listarPorCargo(@PathVariable("idBarbearia") int idBarbearia,
                                                               @PathVariable("cargo") String cargo) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idBarbearia", idBarbearia);
        map.put("cargo", cargo);

        var funcionarios = service.listarPorCargo(map);
        var funcionariosDTO = funcionarios.stream()
                .map(doMain -> (FuncionarioDTO) ConverterDTO.toDTO(doMain, FuncionarioDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<List<FuncionarioDTO>>(funcionariosDTO, HttpStatus.OK);
    }

    @GetMapping("{idBarbearia}")
    public ResponseEntity<List<FuncionarioDTO>> listar(@PathVariable("idBarbearia") int idBarbearia) {

        var barbearia = new Barbearia();
        barbearia.setId(idBarbearia);

        var funcionarios = service.listar(barbearia);
        var funcionariosDTO = funcionarios.stream()
                .map(doMain -> (FuncionarioDTO) ConverterDTO.toDTO(doMain, FuncionarioDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<List<FuncionarioDTO>>(funcionariosDTO,HttpStatus.OK);
    }

}
