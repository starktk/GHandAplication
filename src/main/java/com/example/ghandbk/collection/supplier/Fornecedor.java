package com.example.ghandbk.collection.supplier;

import com.example.ghandbk.collection.Historico;
import com.example.ghandbk.collection.enums.Situacao;
import com.example.ghandbk.collection.schedule.AgendaProduto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Fornecedor {

    private String razaoSocial;
    private String cnpj;
    private Situacao status;
    private List<Historico> historico;

}
