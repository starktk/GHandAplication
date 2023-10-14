package com.example.GHand.document.agenda;

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
public class AgendaPayment extends Agenda {

    @Id
    private String razaoSocial;
    private Integer amountToPay;
    private LocalDate datePay;
}
