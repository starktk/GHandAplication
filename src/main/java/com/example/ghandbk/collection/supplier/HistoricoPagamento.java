package com.example.ghandbk.collection.supplier;

import com.example.ghandbk.collection.enums.SituacaoPagamento;
import com.example.ghandbk.collection.enums.TipoPagamento;
import com.example.ghandbk.collection.schedule.AgendaPagamento;
import com.example.ghandbk.dto.schedule.payment.AgendaPaymentDto;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HistoricoPagamento extends Historico {

    private AgendaPaymentDto agendaPagamento;

}
