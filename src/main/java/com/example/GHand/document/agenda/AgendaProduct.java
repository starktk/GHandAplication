package com.example.GHand.document.agenda;


import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
@Getter
@Setter
public class AgendaProduct extends Agenda {

    @Id
    private String razaoSocial;
    private String nameProduct;
    private SituacaoProduto isReceived;
    private Integer amount;

}
