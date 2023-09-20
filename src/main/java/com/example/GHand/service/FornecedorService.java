package com.example.GHand.service;

import com.example.GHand.document.fornecedor.Fornecedor;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorHistoricoDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.repository.FornecedorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    public final ObjectMapper objectMapper;

    public FornecedorDto addFornecedor(FornecedorRequestDto fornecedorRequestDto) {
      //  if (fornecedorRequestDto.getCnpj() <= 14 && fornecedorRequestDto.getCnpj().compareTo())
        Fornecedor fornecedorSave = objectMapper.convertValue(fornecedorRequestDto, Fornecedor.class);
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorRepository
                .save(fornecedorSave), FornecedorDto.class);
        return fornecedorReturn;
    }

    public Optional<FornecedorDto> findFornecedor(String razaoSocial) {
        Optional<Fornecedor> fornecedorToFind = fornecedorRepository.findById(razaoSocial);

        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorToFind, FornecedorDto.class);
        return Optional.ofNullable(fornecedorReturn);
    }

    public FornecedorHistoricoDto findHistoricoInFornecedor(String razaoSocial) {
        Optional<Fornecedor> fornecedorToFind = fornecedorRepository.findById(razaoSocial);
        FornecedorHistoricoDto fornecedorReturn = objectMapper.convertValue(fornecedorToFind, FornecedorHistoricoDto.class);
        return fornecedorReturn;
    }

    public FornecedorRequestDto findFornecedorToHistorico(String razaoSocial) {

        return objectMapper.convertValue(fornecedorRepository.findById(razaoSocial), FornecedorRequestDto.class);
    }
}
