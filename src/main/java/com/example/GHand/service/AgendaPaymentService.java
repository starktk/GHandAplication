package com.example.GHand.service;

import com.example.GHand.document.agenda.AgendaPayment;
import com.example.GHand.document.agenda.enumns.SituacaoPayment;
import com.example.GHand.dto.agendapayment.AgendaPaymentDto;
import com.example.GHand.dto.agendapayment.AgendaPaymentRequestDto;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.usuario.UsuarioDto;
import com.example.GHand.repository.AgendaPaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class AgendaPaymentService {

    private final AgendaPaymentRepository agendaPaymentRepository;
    private final UsuarioService usuarioService;
    private final FornecedorService fornecedorService;

    private final ObjectMapper objectMapper;

    public AgendaPaymentDto addProductToReceive(AgendaPaymentRequestDto agendaPaymentRequestDto) {
        UsuarioDto userToFind = usuarioService.findUser(agendaPaymentRequestDto.getUsername());

        if (userToFind == null) {
            throw new RuntimeException("Usuario inexistente");
        }
        FornecedorDto fornecedorToFind = fornecedorService.findFornecedor(agendaPaymentRequestDto.getRazaoSocial());
        if (fornecedorToFind == null) {
            throw new RuntimeException("Fornecedor não existe!!");
        }

        LocalDate dateToPay = LocalDate.parse(agendaPaymentRequestDto.getDateToPayment(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        agendaPaymentRequestDto.setStatus(SituacaoPayment.NAO_PAGA);
        AgendaPayment agendaPayment = new AgendaPayment();
        agendaPayment.setRazaoSocial(agendaPaymentRequestDto.getRazaoSocial());
        agendaPayment.setUsername(agendaPaymentRequestDto.getUsername());
        agendaPayment.setMounth(LocalDate.from(dateToPay.getMonth()));
        agendaPayment.setDataToPay(dateToPay);

        AgendaPaymentDto agendaPaymentDto = objectMapper.convertValue(agendaPaymentRepository.save(agendaPayment), AgendaPaymentDto.class);
        return agendaPaymentDto;
    }




}
