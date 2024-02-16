package com.example.ghandbk.collection.supplier;

import com.example.ghandbk.collection.Historico;
import com.example.ghandbk.collection.enums.Situacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Fornecedor {
    @Builder
    public Fornecedor(String razaoSocial, String cnpj, Situacao status, List<Historico> historico) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.status = status;
        this.historico = historico;
    }

    private String razaoSocial;
    private String cnpj;
    private Situacao status;
    private List<Historico> historico;

}
