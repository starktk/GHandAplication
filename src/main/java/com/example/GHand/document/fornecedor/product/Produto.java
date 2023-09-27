package com.example.GHand.document.fornecedor.product;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import lombok.Data;



@Data
public class Produto {


    private String nameProduct;
    private Integer amount;
    private String dateToReceiveOrReceived;
    private SituacaoProduto isReceived;

}
