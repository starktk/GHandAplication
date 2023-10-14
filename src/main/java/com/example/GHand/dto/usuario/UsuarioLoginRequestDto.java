package com.example.GHand.dto.usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioLoginRequestDto {

    private String username;
    private String password;
}
