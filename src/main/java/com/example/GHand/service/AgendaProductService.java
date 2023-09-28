package com.example.GHand.service;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.example.GHand.document.fornecedor.product.AgendaProduct;
import com.example.GHand.dto.agendapayment.AgendaProductToFindDto;
import com.example.GHand.dto.agendaproduct.AgendaProductDto;
import com.example.GHand.dto.agendaproduct.AgendaProductRequestDto;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.repository.AgendaProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        if (agendaProductRequestDto.getIsReceived() == SituacaoProduto.RECEBIDO) {
            throw new RuntimeException("Não é possivel adicionar um produto com status recebido");
        }
        LocalDate dateToSave = LocalDate.parse(agendaProductRequestDto.getDateToReceiveOrReceived(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        AgendaProduct agendaToSave = objectMapper.convertValue(agendaProductRequestDto, AgendaProduct.class);
        agendaToSave.setDateToReceiveOrReceived(dateToSave);
        agendaProductRepository.save(agendaToSave);
        AgendaProductDto agendaProductDto = new AgendaProductDto();
        agendaProductDto.setNomeProduct(agendaToSave.getNameProduct());
        agendaProductDto.setAmount(agendaToSave.getAmount());
        agendaProductDto.setStatus(agendaToSave.getIsReceived());
        agendaProductDto.setDateToReceive(agendaToSave.getDateToReceiveOrReceived());
        return agendaProductDto;
    }

    public AgendaProductDto findDate(AgendaProductToFindDto agendaProductToFindDto) {
        if (agendaProductToFindDto.getRazaoSocial().isEmpty()) {
            throw new RuntimeException("Preencha o campo");
        }
        Optional<AgendaProduct> agendaProduct = agendaProductRepository.findById(agendaProductToFindDto.getRazaoSocial());

        if (agendaProduct.get().getDateToReceiveOrReceived().getMonth().equals(agendaProductToFindDto.getMes())) {
            throw new RuntimeException("Não há recebimento este mês");
        }

        AgendaProductDto agendaProductDto = new AgendaProductDto();
        agendaProductDto.setDateToReceive(agendaProduct.get().getDateToReceiveOrReceived());
        agendaProductDto.setNomeProduct(agendaProduct.get().getNameProduct());
        agendaProductDto.setStatus(agendaProduct.get().getIsReceived());
        agendaProductDto.setAmount(agendaProduct.get().getAmount());
        return agendaProductDto;
    }
}
