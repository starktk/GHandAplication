package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.enums.Situacao;
import com.example.GHand.document.fornecedor.Historico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorRequestDto {

    private String razaoSocial;
    private Integer cnpj;
    private Situacao status;
    private Historico historico;

}
