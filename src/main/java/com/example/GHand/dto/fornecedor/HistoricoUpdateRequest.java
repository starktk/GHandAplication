package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.fornecedor.product.Produto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Data
public class HistoricoUpdateRequest {

    private String razaoSocial;
    private Produto produtcsReceived;
}
