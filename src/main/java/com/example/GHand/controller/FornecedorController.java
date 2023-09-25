package com.example.GHand.controller;

import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getFornecedor/{id}")
    public ResponseEntity<FornecedorDto> findFornecedor(@PathVariable("id") String razaoSocial) {
        return new ResponseEntity<>(fornecedorService.findFornecedor(razaoSocial), HttpStatus.FOUND);
    }

    @PutMapping("alterFornecedor")
    public ResponseEntity<FornecedorDto> alterFornecedor(@RequestBody FornecedorDto fornecedorDto) {
        return new ResponseEntity<>(fornecedorService.alterFornecedor(fornecedorDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteFornecedor/{id}")
    public ResponseEntity deleteFornecedor(@PathVariable("id") String razaoSocial) {
        fornecedorService.deleteFornecedor(razaoSocial);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getAllFornecedores/{id}")
    public ResponseEntity<List<FornecedorDto>> getAllFornecedores(@PathVariable("id") String username) {
        return new ResponseEntity<>(fornecedorService.getAllFornecedores(username), HttpStatus.FOUND);
    }
    @PutMapping("alterStatus")
    public ResponseEntity<FornecedorDto> updateStatus(@RequestBody FornecedorDto fornecedorDto) {
        return new ResponseEntity<>(fornecedorService.changeStatus(fornecedorDto), HttpStatus.ACCEPTED);
    }
}
