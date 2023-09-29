package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.agenda.AgendaProduct;
import lombok.Data;


@Data
public class HistoricoUpdateRequest {

    private String razaoSocial;
    private AgendaProduct produtcsReceived;
}
