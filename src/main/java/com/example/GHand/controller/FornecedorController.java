package com.example.GHand.controller;

import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @PostMapping("createFornecedor")
    public ResponseEntity insertFornecedorWithOneUser(@RequestBody FornecedorRequestDto fornecedorRequestDto) {
        fornecedorService.addFornecedor(fornecedorRequestDto);
        return new ResponseEntity(HttpStatus.CREATED);
    }


}
