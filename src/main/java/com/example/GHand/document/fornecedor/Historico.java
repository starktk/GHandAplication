package com.example.GHand.document.fornecedor;

import com.example.GHand.document.fornecedor.product.Produto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class Historico {

    private Integer amountReceivedProducts;
    private List<Produto> productsReceived;

}
