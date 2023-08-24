package com.example.GHand.service;


import com.example.GHand.dto.usuario.UsuarioDto;
import com.example.GHand.dto.usuario.UsuarioRequestDto;
import com.example.GHand.model.Usuario;
import com.example.GHand.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    private final ObjectMapper objectMapper;

    public UsuarioRequestDto addUser(UsuarioRequestDto usuarioRequestDto) {
        if (usuarioRequestDto.getName().isEmpty() &&
                usuarioRequestDto.getUsername().isEmpty() && usuarioRequestDto.getPassword().isEmpty()) {
            throw new RuntimeException("Campos inválidos");
        }

        Usuario userSave = objectMapper.convertValue(usuarioRequestDto, Usuario.class);
        UsuarioRequestDto userReturn = objectMapper.convertValue(usuarioRepository.save(userSave), UsuarioRequestDto.class);
        return userReturn;
    }

    public UsuarioDto findUser(String name) {
        if (name.isEmpty()) {
            throw new RuntimeException("Temporaria");
        }


        UsuarioDto userReturn = objectMapper.convertValue(usuarioRepository.findById(name), UsuarioDto.class);
        return userReturn;
    }
}
