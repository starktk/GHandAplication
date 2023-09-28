package com.example.GHand.controller;

import com.example.GHand.dto.agendapayment.AgendaProductToFindDto;
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
public class AgendaControllerPayment {

    private final AgendaProductService agendaProductService;

    @PostMapping("agendar")
    public ResponseEntity markDateToProduct(@RequestBody AgendaProductRequestDto agendaProductRequestDto) {
        agendaProductService.markToReceiveProduct(agendaProductRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("findAgenda")
    public ResponseEntity<AgendaProductDto> findAgenda(@RequestBody AgendaProductToFindDto agendaProductToFindDto) {
        return new ResponseEntity(agendaProductService.findDate(agendaProductToFindDto), HttpStatus.FOUND);
    }
}
