package com.example.GHand.controller;

import com.example.GHand.dto.agendaproduct.AgendaProductToFindDto;
import com.example.GHand.dto.agendaproduct.AgendaProductDto;
import com.example.GHand.dto.agendaproduct.AgendaProductRequestDto;
import com.example.GHand.service.AgendaProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agendaproduct")
public class AgendaProductController {

    private final AgendaProductService agendaProductService;

    @PostMapping("agendar")
    public ResponseEntity addProduto(@RequestBody AgendaProductRequestDto agendaProductRequestDto) {
        agendaProductService.markToReceiveProduct(agendaProductRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("findAgenda")
    public ResponseEntity<AgendaProductDto> findProdutoInAgenda(@RequestBody AgendaProductToFindDto agendaProductToFindDto) {
        return new ResponseEntity(agendaProductService.findDate(agendaProductToFindDto), HttpStatus.FOUND);
    }
}
