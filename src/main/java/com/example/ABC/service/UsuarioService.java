package com.example.ABC.service;

import com.example.ABC.model.Usuario;
import com.example.ABC.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario addUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
