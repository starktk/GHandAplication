package com.example.ghandbk.dto.supllier;

import com.example.ghandbk.collection.Historico;
import com.example.ghandbk.collection.enums.Situacao;
import com.example.ghandbk.collection.schedule.AgendaProduto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FornecedorRequestDto {

    private String razaoSocial;
    private String cnpj;
    private Situacao status;
    private Historico historico;
    private String username;
    private String name;
}
