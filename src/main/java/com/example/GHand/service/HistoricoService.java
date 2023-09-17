package com.example.GHand.service;

import com.example.GHand.document.fornecedor.Historico;
import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.example.GHand.document.fornecedor.product.Produto;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorHistoricoDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.dto.fornecedor.HistoricoUpdateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistoricoService {

    private final ObjectMapper objectMapper;

    private final FornecedorService fornecedorService;
    private Historico historico;

    private void addProdutoToHistorico(HistoricoUpdateRequest historicoUpdateRequest) {
        if (historicoUpdateRequest.getProdutcsReceived().getIsReceived() != SituacaoProduto.RECEBIDO) {

        }
        Boolean isFutureDate = historico.getProductsReceived()
                .stream()
                .anyMatch(produto1 -> produto1.getDateToReceiveOrReceived() == historicoUpdateRequest.getProdutcsReceived().getDateToReceiveOrReceived());
        historico.getProductsReceived().add(historicoUpdateRequest.getProdutcsReceived());
    }

 //   private Historico findHistorico() {
 //
 //   }

    private Boolean verifyProductsList() {
       return historico.getProductsReceived().isEmpty();
    }

    public void updateHistorico(HistoricoUpdateRequest historicoUpdateRequest) {
        Optional<FornecedorDto> fornecedorToUpdate =
                fornecedorService.findFornecedor(historicoUpdateRequest.getRazaoSocial());
        if (verifyProductsList()) {

        }
        historico.setAmountReceivedProducts(calculateAmountReceivedProducts(historicoUpdateRequest));
        addProdutoToHistorico(historicoUpdateRequest);
        FornecedorRequestDto fornecedorRequestDto =
                objectMapper.convertValue(fornecedorToUpdate, FornecedorRequestDto.class);
        fornecedorRequestDto.setHistorico(historico);

    }

    private Integer calculateAmountReceivedProducts(HistoricoUpdateRequest historicoUpdateRequest) {
        Integer totalAmount = 0;
        for(Produto produto: historico.getProductsReceived()) {
            totalAmount = historico.getAmountReceivedProducts() + historicoUpdateRequest.getProdutcsReceived().getAmount();
        }
        return totalAmount;
    }
}
