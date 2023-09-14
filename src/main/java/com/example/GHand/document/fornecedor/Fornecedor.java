package com.example.GHand.document.fornecedor;

import com.example.GHand.document.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Fornecedor")
public class Fornecedor {

    @Id
    private String razaoSocial;
    private Situacao status;
    private Integer cnpj;
    private String username;
    private Historico historico;

}
