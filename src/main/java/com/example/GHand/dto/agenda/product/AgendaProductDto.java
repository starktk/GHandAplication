package com.example.GHand.dto.agenda.product;


import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgendaProductDto {

    private LocalDate date;
    private String razaoSocial;
    private String nameProduct;
    private SituacaoProduto isReceived;

}
