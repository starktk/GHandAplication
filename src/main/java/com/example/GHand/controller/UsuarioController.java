package com.example.GHand.controller;

import com.example.GHand.dto.usuario.UsuarioDto;
import com.example.GHand.dto.usuario.UsuarioRequestDto;
import com.example.GHand.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/createUser")
    public ResponseEntity<UsuarioRequestDto> insertUser(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return new ResponseEntity<>(usuarioService.addUser(usuarioRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<UsuarioDto> findUser(@PathVariable ("id") String name) {
        return new ResponseEntity<>(usuarioService.findUser(name), HttpStatus.OK);
    }
}
