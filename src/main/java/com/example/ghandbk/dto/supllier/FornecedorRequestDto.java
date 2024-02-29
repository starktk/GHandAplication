package com.example.ghandbk.dto.supllier;

import com.example.ghandbk.collection.supplier.Historico;
import com.example.ghandbk.collection.enums.Situacao;
import com.example.ghandbk.collection.supplier.HistoricoPagamento;
import com.example.ghandbk.collection.supplier.HistoricoProduto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorRequestDto {

    private String razaoSocial;
    private String cnpj;
    private Situacao status;
    private HistoricoPagamento historicoPagamento;
    private HistoricoProduto historicoProduto;
    private String username;
    private String name;
}
