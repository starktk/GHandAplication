package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.enums.Situacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDto {
    @Builder
    public FornecedorDto(String razaoSocial, Integer cnpj, Situacao status) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.status = status;
    }

    private String razaoSocial;
    private Integer cnpj;
    private Situacao status;

}
