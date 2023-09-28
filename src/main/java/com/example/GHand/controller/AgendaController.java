package com.example.GHand.controller;

import com.example.GHand.dto.agenda.AgendaRequestDto;
import com.example.GHand.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    @PostMapping("agendar")
    public ResponseEntity markDateToReceive(AgendaRequestDto agendaRequestDto) {
        agendaService.addProductToReceive(agendaRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
