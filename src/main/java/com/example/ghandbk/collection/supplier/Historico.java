package com.example.ghandbk.collection.supplier;

import com.example.ghandbk.collection.enums.TipoHistorico;
import com.example.ghandbk.collection.schedule.AgendaPagamento;
import com.example.ghandbk.dto.schedule.AgendaProdDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Historico  {

    private TipoHistorico historicoTipo;

}
