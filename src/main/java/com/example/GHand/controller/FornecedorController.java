package com.example.GHand.controller;

import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.exceptions.NotFoundException;
import com.example.GHand.exceptions.ValueNotAcceptedException;
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
    public ResponseEntity insertFornecedorWithOneUser(@RequestBody FornecedorRequestDto fornecedorRequestDto) throws ValueNotAcceptedException, NotFoundException {
        return new ResponseEntity(fornecedorService.addFornecedor(fornecedorRequestDto),HttpStatus.CREATED);
    }

    @GetMapping("getFornecedor")
    public ResponseEntity<FornecedorDto> findFornecedor(@RequestBody FornecedorRequestDto fornecedorDto) throws ValueNotAcceptedException, NotFoundException {
        return new ResponseEntity(fornecedorService.findFornecedor(fornecedorDto),HttpStatus.FOUND);
    }

    @PutMapping("alterFornecedor")
    public ResponseEntity<FornecedorDto> alterFornecedor(@RequestBody FornecedorRequestDto fornecedorRequestDto) throws ValueNotAcceptedException, NotFoundException {
        return new ResponseEntity(fornecedorService.alterFornecedor(fornecedorRequestDto), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteFornecedor/{id}")
    public ResponseEntity deleteFornecedor(@PathVariable("id") String razaoSocial) throws ValueNotAcceptedException, NotFoundException {
        fornecedorService.deleteFornecedor(razaoSocial);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getAllFornecedores/{id}")
    public ResponseEntity<List<FornecedorDto>> getAllFornecedores(@PathVariable("id") String username) throws ValueNotAcceptedException, NotFoundException {
        return new ResponseEntity(fornecedorService.getAllFornecedores(username), HttpStatus.FOUND);
    }
    @PutMapping("alterStatus")
    public ResponseEntity<FornecedorDto> updateStatus(@RequestBody FornecedorDto fornecedorDto) {
        return fornecedorService.changeStatus(fornecedorDto);
    }
}
