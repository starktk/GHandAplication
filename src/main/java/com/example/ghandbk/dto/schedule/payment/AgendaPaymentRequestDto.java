package com.example.ghandbk.dto.schedule.payment;

import com.example.ghandbk.collection.enums.SituacaoPagamento;
import com.example.ghandbk.collection.enums.SituacaoProduto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AgendaPaymentRequestDto {

    private String username;
    private String name;
    private String cnpj;
    private Double valueToPay;
    private SituacaoPagamento situacaoPagamento;
    private LocalDate dateToPayOrReceive;


}
