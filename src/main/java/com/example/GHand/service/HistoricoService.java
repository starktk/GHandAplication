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


    private Historico getInstance() {
        return new Historico();
    }

    private void addProdutoToHistorico(HistoricoUpdateRequest historicoUpdateRequest) {
        if (historicoUpdateRequest.getProdutcsReceived().getIsReceived() != SituacaoProduto.RECEBIDO) {

        }
        Boolean isFutureDate = getInstance().getProductsReceived()
                .stream()
                .anyMatch(produto1 -> produto1.getDateToReceiveOrReceived() == historicoUpdateRequest.getProdutcsReceived().getDateToReceiveOrReceived());
        getInstance().getProductsReceived().add(historicoUpdateRequest.getProdutcsReceived());
    }

 //   private Historico findHistorico() {
 //
 //   }



    public void updateHistorico(HistoricoUpdateRequest historicoUpdateRequest) {
        Optional<FornecedorDto> fornecedorToUpdate =
                fornecedorService.findFornecedor(historicoUpdateRequest.getRazaoSocial());
        verifyProductsList();
        addProdutoToHistorico(historicoUpdateRequest);
        getInstance().setAmountReceivedProducts(calculateAmountReceivedProducts(historicoUpdateRequest));
        FornecedorRequestDto fornecedorRequestDto =
                objectMapper.convertValue(fornecedorToUpdate, FornecedorRequestDto.class);
        fornecedorRequestDto.setHistorico(getInstance());


    }

    private Integer calculateAmountReceivedProducts(HistoricoUpdateRequest historicoUpdateRequest) {
        Integer totalAmount = 0;
//        if (!verifyProductsList()) {
            for(Produto produto: getInstance().getProductsReceived()) {
                totalAmount = getInstance().getAmountReceivedProducts() + historicoUpdateRequest.getProdutcsReceived().getAmount();
            }
//        }
        return totalAmount;
    }

    private Boolean verifyProductsList() {
        if (getInstance().getProductsReceived() == null
        ) {
            return true;
        }
        return false;
    }
}
