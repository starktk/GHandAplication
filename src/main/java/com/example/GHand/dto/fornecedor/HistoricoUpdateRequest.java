package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.fornecedor.product.Produto;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class HistoricoUpdateRequest {

    private String razaoSocial;
    private Produto produtcsReceived;
}
