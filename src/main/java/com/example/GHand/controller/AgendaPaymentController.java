package com.example.GHand.controller;

import com.example.GHand.dto.agendapayment.AgendaPaymentRequestDto;
import com.example.GHand.service.AgendaPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agendapayment")
public class AgendaPaymentController {

    private final AgendaPaymentService agendaPaymentService;

    @PostMapping("agendar")
    public ResponseEntity markDateToReceive(AgendaPaymentRequestDto agendaPaymentRequestDto) {
        agendaPaymentService.addProductToReceive(agendaPaymentRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
