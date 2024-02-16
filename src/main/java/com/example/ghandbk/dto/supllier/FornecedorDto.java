package com.example.ghandbk.dto.supllier;

import com.example.ghandbk.collection.enums.Situacao;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDto {
    @Builder
    public FornecedorDto(String razaoSocial, String cnpj, Situacao status) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.status = status;
    }

    private String razaoSocial;
    private String cnpj;
    private Situacao status;

}
