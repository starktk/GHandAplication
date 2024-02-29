package com.example.ghandbk.dto.schedule.product;

import com.example.ghandbk.collection.enums.SituacaoProduto;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AgendaProdDto {
    @Builder
    public AgendaProdDto(String nameProduct, Integer amount, SituacaoProduto status, LocalDate dateToPayOrReceive, FornecedorDto fornecedorDto) {
        this.nameProduct = nameProduct;
        this.amount = amount;
        this.status = status;
        this.dateToPayOrReceive = dateToPayOrReceive;
        this.fornecedorDto = fornecedorDto;
    }

    private String nameProduct;
    private Integer amount;
    private SituacaoProduto status;
    private LocalDate dateToPayOrReceive;
    private FornecedorDto fornecedorDto;
}
