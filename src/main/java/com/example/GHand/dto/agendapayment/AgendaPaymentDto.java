package com.example.GHand.dto.agendapayment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgendaPaymentDto {

    private String razaoSocial;
    private LocalDate mes;
}
