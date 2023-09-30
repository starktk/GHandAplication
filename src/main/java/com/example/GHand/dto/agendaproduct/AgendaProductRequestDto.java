package com.example.GHand.dto.agendaproduct;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AgendaProductRequestDto {

    private String razaoSocial;
    private String nameProduct;
    private Integer amount;
    private LocalDate dateToReceiveOrReceived;
    private SituacaoProduto isReceived;
}
