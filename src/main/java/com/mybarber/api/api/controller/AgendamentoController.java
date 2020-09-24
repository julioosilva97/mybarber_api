package com.mybarber.api.api.controller;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mybarber.api.api.util.ConverterDTO;
import com.mybarber.api.domain.entity.Barbearia;
import com.mybarber.api.domain.enumfactory.AgendamentoAgendado;
import com.mybarber.api.domain.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mybarber.api.api.dto.agendamento.AgendamentoDTO;
import com.mybarber.api.api.dto.agendamento.AgendamentoDTOInput;
import com.mybarber.api.api.dto.agendamento.EventoDTO;
import com.mybarber.api.api.dto.relatorio.RelatorioDTO;
import com.mybarber.api.domain.entity.Agendamento;

@RestController
@RequestMapping("api/agendamentos")
public class AgendamentoController {


    @Autowired
    AgendamentoService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@Valid @RequestBody AgendamentoDTOInput agendamentoDtoInput) {

        var agendamento = (Agendamento) ConverterDTO.toDoMain(agendamentoDtoInput, AgendamentoAgendado.class);

        service.salvar(agendamento);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/listarFullCalendar/{idBarbeiro}")
    public ResponseEntity<List<EventoDTO>> listarPorBarbeiro(@PathVariable("idBarbeiro") int id) {


        var agendamentos = service.listarPorBarbeiro(id);


        var eventosDTO = agendamentos.stream()
                .map(agendamento -> new EventoDTO(agendamento.getId(), agendamento.getCliente().getNome() , agendamento.gerarColor(),
						agendamento.getDataHorarioInicio(), agendamento.getDataHorarioFim()))
                .collect(Collectors.toList());


        return new ResponseEntity<List<EventoDTO>>(eventosDTO, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<AgendamentoDTO> buscarPorId(@PathVariable("id") int id) {

        var agendamentoDTO = (AgendamentoDTO) ConverterDTO.toDTO(service.buscarPorId(id),
                AgendamentoDTO.class);

        return new ResponseEntity<AgendamentoDTO>(agendamentoDTO, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> editar(@Valid @RequestBody AgendamentoDTOInput agendamentoDTOImput) {

        var agendamento = (Agendamento) ConverterDTO.toDoMain(agendamentoDTOImput,AgendamentoAgendado.class);

        service.editar(agendamento);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/alterarStatus")
    public ResponseEntity<Void> alterarStatus(@RequestBody AgendamentoDTO agendamentoDto) {

        var agendamento = (Agendamento) ConverterDTO.toDoMain(agendamentoDto,Agendamento.class);

        service.alterarStatus(agendamento);

        return new ResponseEntity<Void>(HttpStatus.OK);
    }


    @GetMapping("/buscarPorData/{data}/{idBarbeiro}")
    public ResponseEntity<List<Agendamento>> buscarPorData(@PathVariable("data") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate data, @PathVariable("idBarbeiro") int idBarbeiro) {


        return new ResponseEntity<List<Agendamento>>(service.buscarPorData(data, idBarbeiro), HttpStatus.OK);

    }

    @GetMapping("somaValorMensal/{idBarbearia}")
    public ResponseEntity<List<RelatorioDTO>> valorTotalMensal(@PathVariable("id") int idBarbearia, LocalDate data) {

        var barbearia = new Barbearia();
        barbearia.setId(idBarbearia);

        var valores = service.somaValorMensal(barbearia, data);

        var valoresDTO = valores.stream()
                .map(valor -> (RelatorioDTO) ConverterDTO.toDTO(valor,RelatorioDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<List<RelatorioDTO>>(valoresDTO, HttpStatus.OK);

    }

}
