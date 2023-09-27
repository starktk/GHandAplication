package com.example.GHand.dto.agenda;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgendaDto {

    private String razaoSocial;
    private LocalDate mes;
}
