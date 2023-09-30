package com.example.GHand.service;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.example.GHand.document.agenda.AgendaProduct;
import com.example.GHand.dto.agendaproduct.AgendaProductToFindDto;
import com.example.GHand.dto.agendaproduct.AgendaProductDto;
import com.example.GHand.dto.agendaproduct.AgendaProductRequestDto;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.repository.AgendaProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AgendaProductService {

    private final AgendaProductRepository agendaProductRepository;
    private final FornecedorService fornecedorService;

    private final ObjectMapper objectMapper;
    public AgendaProductDto markToReceiveProduct(AgendaProductRequestDto agendaProductRequestDto) {
        FornecedorDto fornecedorDto = fornecedorService.findFornecedor(agendaProductRequestDto.getRazaoSocial());

        if (fornecedorDto == null) {
            throw new RuntimeException("Fornecedor não existe");
        }
        if (agendaProductRequestDto.getRazaoSocial().isEmpty()) {
            throw new RuntimeException("Preencha o campo!!");
        }

        AgendaProduct agendaToSave = objectMapper.convertValue(agendaProductRequestDto, AgendaProduct.class);
        agendaProductRepository.save(agendaToSave);
        AgendaProductDto agendaReturn = objectMapper.convertValue(agendaToSave, AgendaProductDto.class);
        return agendaReturn;
    }

    public AgendaProductDto findDate(AgendaProductToFindDto agendaProductToFindDto) {
        if (agendaProductToFindDto.getRazaoSocial().isEmpty()) {
            throw new RuntimeException("Preencha o campo");
        }
        Optional<AgendaProduct> agendaProduct = agendaProductRepository.findById(agendaProductToFindDto.getRazaoSocial());

        if (agendaProduct.get().getDateToReceiveOrReceived().equals(agendaProductToFindDto.getMes())) {
            throw new RuntimeException("Não há recebimento este mês");
        }

        AgendaProductDto agendaProductDto = new AgendaProductDto();
        agendaProductDto.setDateToReceive(agendaProduct.get().getDateToReceiveOrReceived());
        agendaProductDto.setNomeProduct(agendaProduct.get().getNameProduct());
        agendaProductDto.setStatus(agendaProduct.get().getIsReceived());
        agendaProductDto.setAmount(agendaProduct.get().getAmount());
        return agendaProductDto;
    }

    public Optional<AgendaProduct> find(String razaoSocial) {
        return agendaProductRepository.findById(razaoSocial);
    }
}
