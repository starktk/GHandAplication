package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.enums.Situacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDto {

    private String razaoSocial;
    private Situacao status;

}
