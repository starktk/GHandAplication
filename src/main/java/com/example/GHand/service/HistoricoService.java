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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistoricoService {

    private final ObjectMapper objectMapper;

    private final FornecedorService fornecedorService;


    private Historico historico;

    public void addProdutoToHistorico(HistoricoUpdateRequest historicoUpdateRequest) {
        FornecedorRequestDto fornecedor = fornecedorService.findFornecedorToHistorico(historicoUpdateRequest.getRazaoSocial());
        if (fornecedor == null || historicoUpdateRequest.getRazaoSocial().isEmpty()) {
            throw new RuntimeException("Fornecedor inexistente");
        } else if (historicoUpdateRequest.getProdutcsReceived().getIsReceived() != SituacaoProduto.RECEBIDO) {
            throw new RuntimeException("Situação aberta");
        } else if (verifyFutureDate(historicoUpdateRequest)) {
            throw new RuntimeException("Data futura");
        }
        historico.addList(historicoUpdateRequest.getProdutcsReceived());
        updateHistorico(historicoUpdateRequest, fornecedor);
    }

 //   private Historico findHistorico() {
 //
 //   }



    private void updateHistorico(HistoricoUpdateRequest historicoUpdateRequest, FornecedorRequestDto fornecedorRequestDto) {
        Integer amountFinal = calculateAmountReceivedProducts(historicoUpdateRequest);
        if (amountFinal <= 0) {
            historico.setAmountReceivedProducts(historicoUpdateRequest.getProdutcsReceived().getAmount());
        }
        historico.setAmountReceivedProducts(amountFinal);
        if (verifyProductsList()) {
            throw new RuntimeException("Produtos vazios");
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate.parse(historicoUpdateRequest.getProdutcsReceived().getDateToReceiveOrReceived().toString(), formato);
        historico.setProductsReceived(historico.getProductsReceived());
        fornecedorRequestDto.setHistorico(historico);
        fornecedorService.addFornecedor(fornecedorRequestDto);
    }

    private Integer calculateAmountReceivedProducts(HistoricoUpdateRequest historicoUpdateRequest) {
        Integer totalAmount = 0;
       if (!verifyProductsList()) {
            for(Produto produto: historico.getProductsReceived()) {
                totalAmount = historico.getAmountReceivedProducts() + historicoUpdateRequest.getProdutcsReceived().getAmount();
            }
        }
        return totalAmount;
    }

    private Boolean verifyProductsList() {
        return historico.getProductsReceived().isEmpty();
    }
    private Boolean verifyFutureDate(HistoricoUpdateRequest historicoUpdateRequest) {
        return historicoUpdateRequest.getProdutcsReceived()
                .getDateToReceiveOrReceived().isAfter(LocalDate.now());
    }
}
