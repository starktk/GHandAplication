package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.fornecedor.product.AgendaProduct;
import lombok.Data;


@Data
public class HistoricoUpdateRequest {

    private String razaoSocial;
    private AgendaProduct produtcsReceived;
}
