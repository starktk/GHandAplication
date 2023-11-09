package com.example.GHand.service;


import com.example.GHand.document.agenda.AgendaProduct;
import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.example.GHand.dto.agenda.product.AgendaProductDto;
import com.example.GHand.dto.agenda.product.AgendaProductRequestDto;
import com.example.GHand.repository.AgendaProductRepository;
import com.example.GHand.simpleRules.SimpleRules;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgendaProductService {

    private final AgendaProductRepository agendaProductRepository;
    private final FornecedorService fornecedorService;
    private SimpleRules simpleRules;
    private final ObjectMapper objectMapper;



    public ResponseEntity toScheduleDate(AgendaProductRequestDto agendaProductRequestDto) {
        if(agendaProductRequestDto.getRazaoSocial().isEmpty()) {
            return new ResponseEntity("Razão social inválida", HttpStatus.NOT_ACCEPTABLE);
        } else if(!verifyPastDate(agendaProductRequestDto.getDateReceived())) {
            return new ResponseEntity("Data inválida", HttpStatus.NOT_ACCEPTABLE);
        } else if (agendaProductRequestDto.getAmount() <= 0) {
            return new ResponseEntity("Quantidade inválida", HttpStatus.NOT_ACCEPTABLE);
        }
        AgendaProduct agendaToSave = objectMapper.convertValue(agendaProductRequestDto, AgendaProduct.class);
        agendaToSave.setIsReceived(SituacaoProduto.NAO_RECEBIDO);
        agendaToSave.setMarkDate(agendaProductRequestDto.getDateReceived());
        return new ResponseEntity(agendaProductRepository.save(agendaToSave), HttpStatus.CREATED);
    }

    public ResponseEntity<AgendaProductDto> findAgendaById(String razaoSocial) {
        if (razaoSocial.isEmpty()) {
            return new ResponseEntity("Razão social inválida", HttpStatus.NOT_ACCEPTABLE);

        }
        Optional<AgendaProduct> agendaProduct = agendaProductRepository.findById(razaoSocial);
        AgendaProductDto agendaProductDto = objectMapper.convertValue(agendaProduct, AgendaProductDto.class);
        agendaProductDto.setDate(agendaProduct.get().getMarkDate());
        return new ResponseEntity(agendaProductDto, HttpStatus.FOUND);
    }

 //  public ResponseEntity<List<AgendaProductDto>> findAllByMonths(AgendaProductDto agendaProductDto) {
 //    List<AgendaProduct> agendaProducts = agendaProductRepository.findAllByrazaoSocialAndmarkDate(agendaProductDto.getRazaoSocial(),
 //            LocalDate.from(agendaProductDto.getDate().getMonth()));
 //    List<AgendaProductDto> agendaForReturn = agendaProducts.stream()
 //            .map(mapper -> new AgendaProductDto(mapper.getRazaoSocial(), ))
 //
 //   }
    public AgendaProductDto changeStatus(String razaoSocial, String username) {
        if (razaoSocial.isEmpty() && username.isEmpty()) {

        }
        AgendaProduct agendaToSave = agendaProductRepository.findAgendaProductByrazaoSocialAndusername(razaoSocial, username);
        agendaToSave.setIsReceived(SituacaoProduto.RECEBIDO);
        agendaProductRepository.save(agendaToSave);

        AgendaProductDto agendaDtotoSave = new AgendaProductDto();
        agendaDtotoSave.setNameProduct(agendaToSave.getNameProduct());
        agendaDtotoSave.setRazaoSocial(agendaToSave.getRazaoSocial());
        agendaDtotoSave.setIsReceived(agendaToSave.getIsReceived());
        agendaDtotoSave.setDate(agendaToSave.getMarkDate());
        return agendaDtotoSave;
    }
    private Boolean verifyPastDate(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }

    public Boolean verifySituacao(SituacaoProduto situacaoProduto) {
        if (SituacaoProduto.RECEBIDO == situacaoProduto) {
            return true;
        }
        return false;
    }

   public List<AgendaProduct> addToHistorico(AgendaProduct agendaProduct) {
        if (verifySituacao(agendaProduct.getIsReceived())) {

        }
        if (agendaProduct.getMarkDate().isAfter(LocalDate.now())) {

        }
        List<AgendaProduct> agendaToSave = null;
        agendaToSave.add(agendaProduct);
        return agendaToSave;
   }


}
