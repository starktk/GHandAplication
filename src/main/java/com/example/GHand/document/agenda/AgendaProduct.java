package com.example.GHand.document.agenda;

import com.example.GHand.document.fornecedor.enums.SituacaoProduto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class AgendaProduct {

    @Id
    private String razaoSocial;
    private String nameProduct;
    private Integer amount;
    private LocalDate dateToReceiveOrReceived;
    private SituacaoProduto isReceived;

}
