package com.example.GHand.document.fornecedor;

import com.example.GHand.document.enums.Situacao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Fornecedor")
public class Fornecedor {

    @Id
    private String razaoSocial;
    private String cnpj;
    private Situacao status;
    private String username;

}
