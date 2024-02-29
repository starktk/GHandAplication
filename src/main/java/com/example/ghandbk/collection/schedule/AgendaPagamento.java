package com.example.ghandbk.collection.schedule;

import com.example.ghandbk.collection.enums.SituacaoPagamento;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AgendaPagamento extends Agenda {

    private Double valueToPay;
    private SituacaoPagamento situacaoPagamento;
    private FornecedorDto fornecedorDto;
}
