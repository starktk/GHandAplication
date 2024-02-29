package com.example.ghandbk.service;

import com.example.ghandbk.collection.enums.SituacaoPagamento;
import com.example.ghandbk.collection.schedule.AgendaPagamento;
import com.example.ghandbk.dto.schedule.payment.AgendaPaymentRequestDto;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import com.example.ghandbk.dto.supllier.FornecedorRequestDto;
import com.example.ghandbk.exceptions.InvalidValueException;
import com.example.ghandbk.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendaPaymentService {

    private final FornecedorService fornecedorService;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;

    public void setNewSchedule(AgendaPaymentRequestDto agendaPaymentRequestDto) throws InvalidValueException, NotFoundException {
        if (agendaPaymentRequestDto.getCnpj().isEmpty()) throw new InvalidValueException("Cnpj inválido");
        if (agendaPaymentRequestDto.getSituacaoPagamento().toString().isEmpty() || agendaPaymentRequestDto.getSituacaoPagamento().equals(SituacaoPagamento.PAGA)) throw new InvalidValueException("Status inválido");
        if (agendaPaymentRequestDto.getValueToPay() <= 0) throw new InvalidValueException("Valor inválido");
        AgendaPagamento agenda = objectMapper.convertValue(agendaPaymentRequestDto, AgendaPagamento.class);
        FornecedorRequestDto fornecedorRequestDto = new FornecedorRequestDto();
        fornecedorRequestDto.setUsername(agendaPaymentRequestDto.getUsername());
        fornecedorRequestDto.setCnpj(agendaPaymentRequestDto.getCnpj());
        FornecedorDto fornecedorToSave = fornecedorService.getFornecedorByCnpj(fornecedorRequestDto);
        agenda.setFornecedorDto(fornecedorToSave);


    }
}
