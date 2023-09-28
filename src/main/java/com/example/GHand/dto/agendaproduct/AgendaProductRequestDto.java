package com.example.GHand.dto.agendaproduct;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AgendaProductRequestDto {

    private String razaoSocial;
    private String nameProduct;
    private Integer amount;
    private String dateToReceiveOrReceived;
    private SituacaoProduto isReceived;
}
