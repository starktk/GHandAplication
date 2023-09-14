package com.example.GHand.dto.fornecedor;

import com.example.GHand.document.fornecedor.Historico;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorHistoricoDto {

    private String razaoSocial;
    private Historico historico;
}
