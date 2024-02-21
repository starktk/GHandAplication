package com.example.ghandbk.dto.user;

import com.example.ghandbk.collection.schedule.AgendaProduto;
import com.example.ghandbk.collection.supplier.Fornecedor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDto {

    private String username;
    private String name;
    private String password;
    private Fornecedor fornecedor;
    private AgendaProduto agendaProduto;
}
