package com.example.ABC.controller;

import com.example.ABC.model.Usuario;
import com.example.ABC.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/User")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario insert(@RequestBody Usuario usuario) {
        return usuarioService.addUser(usuario);
    }
}
