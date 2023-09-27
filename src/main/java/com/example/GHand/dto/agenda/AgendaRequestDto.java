package com.example.GHand.dto.agenda;

import com.example.GHand.document.fornecedor.product.Produto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AgendaRequestDto {

    private String razaoSocial;
    private Produto produto;
    private String username;

}
