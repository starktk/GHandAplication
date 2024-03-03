package com.example.ghandbk.dto.schedule.payment;

import com.example.ghandbk.collection.enums.SituacaoPagamento;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AgendaPaymentDto {

    private LocalDate dateToPayOrReceive;
    private Double valueToPay;
    private SituacaoPagamento situacaoPagamento;
    private FornecedorDto fornecedorDto;
}
