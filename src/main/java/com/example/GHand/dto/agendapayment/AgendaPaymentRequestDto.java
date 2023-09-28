package com.example.GHand.dto.agendapayment;

import com.example.GHand.document.agenda.enumns.SituacaoPayment;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendaPaymentRequestDto {

    private String razaoSocial;
    private String dateToPayment;
    private String username;
    private SituacaoPayment status;

}
