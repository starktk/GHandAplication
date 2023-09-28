package com.example.GHand.dto.agendaproduct;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class AgendaProductDto {


    private String nomeProduct;
    private Integer amount;
    private LocalDate dateToReceive;
    private SituacaoProduto status;
}
