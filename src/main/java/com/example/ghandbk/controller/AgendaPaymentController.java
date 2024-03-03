package com.example.ghandbk.controller;

import com.example.ghandbk.dto.schedule.payment.AgendaPaymentDto;
import com.example.ghandbk.dto.schedule.payment.AgendaPaymentRequestDto;
import com.example.ghandbk.exceptions.InvalidValueException;
import com.example.ghandbk.exceptions.NotAuthorizedException;
import com.example.ghandbk.exceptions.NotFoundException;
import com.example.ghandbk.service.AgendaPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agendaPagamento")
public class AgendaPaymentController {

    private final AgendaPaymentService agendaPaymentService;

    @PostMapping("/setDateToPay")
    public ResponseEntity setDateToPay(@RequestBody AgendaPaymentRequestDto agendaPaymentRequestDto) throws InvalidValueException, NotFoundException, NotAuthorizedException {
        return new ResponseEntity(agendaPaymentService.setNewSchedule(agendaPaymentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/findPaymentsByMonth")
    public ResponseEntity<List<AgendaPaymentDto>> findPaymentsByMonth(@RequestBody AgendaPaymentRequestDto agendaPaymentRequestDto) throws InvalidValueException, NotFoundException {
        return new ResponseEntity(agendaPaymentService.findAgendaByMonth(agendaPaymentRequestDto), HttpStatus.FOUND);
    }

    @GetMapping("/findPaymentsByStatus")
    public ResponseEntity<List<AgendaPaymentDto>> findPaymentsByStatus(@RequestBody AgendaPaymentRequestDto agendaPaymentRequestDto) throws InvalidValueException, NotFoundException {
        return new ResponseEntity(agendaPaymentService.findAgendaByStatus(agendaPaymentRequestDto), HttpStatus.FOUND);
    }

    @DeleteMapping("/deletePayment")
    public ResponseEntity deletePayment(@RequestBody AgendaPaymentRequestDto agendaPaymentRequestDto) throws InvalidValueException, NotFoundException, NotAuthorizedException {
        agendaPaymentService.deletePayment(agendaPaymentRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<AgendaPaymentDto> updateStatus(@RequestBody AgendaPaymentRequestDto agendaPaymentRequestDto) throws InvalidValueException, NotFoundException, NotAuthorizedException {
        return new ResponseEntity(agendaPaymentService.modifyStatus(agendaPaymentRequestDto), HttpStatus.ACCEPTED);
    }
}
