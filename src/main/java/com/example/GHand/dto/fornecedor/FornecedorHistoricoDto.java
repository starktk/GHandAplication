package com.example.GHand.dto.fornecedor;


import com.example.GHand.document.fornecedor.product.Produto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FornecedorHistoricoDto {

    private Integer amountReceivedProducts;
    private List<Produto> produtcsReceived;
}
