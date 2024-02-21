package com.example.ghandbk.collection.schedule;

import com.example.ghandbk.collection.enums.SituacaoProduto;
import com.example.ghandbk.collection.supplier.Fornecedor;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendaProduto extends Agenda{

    private String nameProduct;
    private Integer amount;
    private SituacaoProduto status;
    private FornecedorDto fornecedor;
}
