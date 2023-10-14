package com.example.GHand.document.historico;

import com.example.GHand.document.agenda.AgendaPayment;
import com.example.GHand.document.agenda.AgendaProduct;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Document
@Getter
@Setter
public class Historico {

    @Id
    private String username;
    private List<AgendaPayment> historyPayment;
    private List<AgendaProduct> historyProduct;

}
