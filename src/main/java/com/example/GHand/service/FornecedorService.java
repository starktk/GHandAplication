package com.example.GHand.service;

import com.example.GHand.document.fornecedor.Fornecedor;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.repository.FornecedorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    public final ObjectMapper objectMapper;

    public FornecedorService(FornecedorRepository fornecedorRepository, ObjectMapper objectMapper) {
        this.fornecedorRepository = fornecedorRepository;
        this.objectMapper = objectMapper;
    }

    public FornecedorDto addFornecedor(FornecedorRequestDto fornecedorRequestDto) {
      //  if (fornecedorRequestDto.getCnpj() <= 14 && fornecedorRequestDto.getCnpj().compareTo())
        Fornecedor fornecedorSave = objectMapper.convertValue(fornecedorRequestDto, Fornecedor.class);
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorRepository
                .save(fornecedorSave), FornecedorDto.class);
        return fornecedorReturn;
    }

    public Optional<Fornecedor> findFornecedor(String razaoSocial) {
        Optional<Fornecedor> fornecedorReturn = fornecedorRepository.findById(razaoSocial);
        return fornecedorReturn;
    }
}
