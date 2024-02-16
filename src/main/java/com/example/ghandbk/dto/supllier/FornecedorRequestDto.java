package com.example.ghandbk.dto.supllier;

import com.example.ghandbk.collection.Historico;
import com.example.ghandbk.collection.enums.Situacao;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FornecedorRequestDto {

    private String razaoSocial;
    private String cnpj;
    private Situacao status;
    private List<Historico> historico;
    private String username;
    private String name;
}
