package com.example.GHand.controller;

import com.example.GHand.dto.agenda.product.AgendaProductDto;
import com.example.GHand.dto.agenda.product.AgendaProductRequestDto;
import com.example.GHand.service.AgendaProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("agendaproduct")
public class AgendaProductController {

    private final AgendaProductService agendaProductService;

    @PostMapping("agendar")
    public ResponseEntity addProduto(@RequestBody AgendaProductRequestDto agendaProductRequestDto) {
        return agendaProductService.toScheduleDate(agendaProductRequestDto);
    }

    @GetMapping("/findAgenda/{id}")
    public ResponseEntity<AgendaProductDto> findAgenda(@PathVariable("id") String razaoSocial) {
        return agendaProductService.findAgendaById(razaoSocial);
    }

}
