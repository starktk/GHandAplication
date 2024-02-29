package com.example.ghandbk.collection.supplier;

import com.example.ghandbk.collection.enums.SituacaoPagamento;
import com.example.ghandbk.collection.enums.TipoPagamento;
import com.example.ghandbk.dto.supllier.FornecedorDto;

import java.math.BigDecimal;

public class HistoricoPagamento {

    private BigDecimal valueToPay;

    private SituacaoPagamento situacaoPagamento;

    private FornecedorDto fornecedorDto;

    private TipoPagamento tipoPagamento;
}
