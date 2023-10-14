package com.example.GHand.service;


import com.example.GHand.document.agenda.AgendaProduct;
import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.example.GHand.dto.agenda.product.AgendaProductRequestDto;
import com.example.GHand.repository.AgendaProductRepository;
import com.example.GHand.simpleRules.SimpleRules;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgendaProductService {

    private final AgendaProductRepository agendaProductRepository;
    private final FornecedorService fornecedorService;
    private final SimpleRules simpleRules;
    private final ObjectMapper objectMapper;



    public void toScheduleDate(AgendaProductRequestDto agendaProductRequestDto) {
        if(simpleRules.verifyString(agendaProductRequestDto.getRazaoSocial())) {

        } else if(!verifyPastDate(agendaProductRequestDto.getDateReceived())) {

        } else if (simpleRules.verifyNumber(agendaProductRequestDto.getAmount())) {

        }
        AgendaProduct agendaToSave = objectMapper.convertValue(agendaProductRequestDto, AgendaProduct.class);
        agendaToSave.setIsReceived(SituacaoProduto.NAO_RECEBIDO);
        agendaProductRepository.save(agendaToSave);


    }

    private Boolean verifyPastDate(LocalDate date) {
        if (!date.isAfter(LocalDate.now())) {
            return false;
        }
        return true;
    }
   // public ResponseEntity addToHistorico() {

 //   }

}
