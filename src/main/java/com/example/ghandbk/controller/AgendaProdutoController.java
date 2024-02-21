package com.example.ghandbk.controller;

import com.example.ghandbk.collection.schedule.AgendaProduto;
import com.example.ghandbk.dto.schedule.AgendaProdutoRequestDto;
import com.example.ghandbk.exceptions.InvalidValueException;
import com.example.ghandbk.exceptions.NotAuthorizedException;
import com.example.ghandbk.exceptions.NotFoundException;
import com.example.ghandbk.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaProdutoController {

    private final AgendaService agendaService;

    @PostMapping("/setDateToReceive")
    public ResponseEntity<AgendaProduto> setDateToReceive(@RequestBody AgendaProdutoRequestDto agendaProdutoRequestDto) throws InvalidValueException, NotFoundException, NotAuthorizedException {
        return new ResponseEntity(agendaService.insertNewSchedule(agendaProdutoRequestDto), HttpStatus.CREATED);
    }
}
