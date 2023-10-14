package com.example.GHand.service;

import com.example.GHand.document.enums.Situacao;
import com.example.GHand.document.fornecedor.Fornecedor;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.repository.FornecedorRepository;
import com.example.GHand.simpleRules.SimpleRules;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FornecedorService {

    private SimpleRules simpleRules;
    private final FornecedorRepository fornecedorRepository;

    private final UsuarioService usuarioService;
    public final ObjectMapper objectMapper;

    public FornecedorDto addFornecedor(FornecedorRequestDto fornecedorRequestDto) {
        if (usuarioService.findUser(fornecedorRequestDto.getUsername()) == null) {
            throw new RuntimeException("Username inexistente!!");
        }
        if (fornecedorRequestDto.getCnpj() > 11) {
            throw new RuntimeException("Cnpj inválido");
        } else if (fornecedorRequestDto.getRazaoSocial().isEmpty()) {
            throw new RuntimeException("Preencher razão social");
        } else if(fornecedorRepository.findById(fornecedorRequestDto.getRazaoSocial()).isPresent()) {
            throw new RuntimeException("Usuario já existente");
        }
        Fornecedor fornecedorSave = objectMapper.convertValue(fornecedorRequestDto, Fornecedor.class);
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorRepository
                .save(fornecedorSave), FornecedorDto.class);
        return fornecedorReturn;
    }

    public FornecedorDto findFornecedor(String razaoSocial) {
        if(simpleRules.verifyString(razaoSocial)) {

        }
        Optional<Fornecedor> fornecedorToFind = fornecedorRepository.findById(razaoSocial);
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorToFind, FornecedorDto.class);
        return fornecedorReturn;
    }
    public FornecedorDto alterFornecedor(FornecedorDto fornecedorDto) {
        if (simpleRules.verifyString(fornecedorDto.getRazaoSocial())) {

        }
        if (fornecedorRepository.findById(fornecedorDto.getRazaoSocial()).isPresent()) {
            throw new RuntimeException("Fornecedor não encontrado");
        }
        if (simpleRules.verifyNumber(fornecedorDto.getCnpj())) {
            throw new RuntimeException("Preencher os campos");
        } else if (fornecedorDto.getStatus() != Situacao.ATIVA || fornecedorDto.getStatus() != Situacao.INATIVA) {
            throw new RuntimeException("Situação do fornecedor não aceita!!");
        }
        Fornecedor fornecedorToSave = objectMapper.convertValue(fornecedorRepository
                .findById(fornecedorDto.getRazaoSocial()), Fornecedor.class);
        fornecedorToSave.setRazaoSocial(fornecedorDto.getRazaoSocial());
        fornecedorToSave.setCnpj(fornecedorDto.getCnpj());
        fornecedorToSave.setStatus(fornecedorDto.getStatus());
        return objectMapper.convertValue(fornecedorToSave, FornecedorDto.class);
    }

    public void deleteFornecedor(String razaoSocial) {
        if (simpleRules.verifyString(razaoSocial)) {
            throw new RuntimeException("Preencha a razão social");
        } else if (!fornecedorRepository.findById(razaoSocial).isPresent()) {
            throw new RuntimeException("Usuario não existente");
        }
        fornecedorRepository.deleteById(razaoSocial);
    }
    public FornecedorDto changeStatus(FornecedorDto fornecedorDto) {
        if(simpleRules.verifyString(fornecedorDto.getRazaoSocial())) {

        }
        Fornecedor fornecedorToUpdate = objectMapper.convertValue(fornecedorRepository.findById(fornecedorDto.getRazaoSocial()), Fornecedor.class);
        if (fornecedorDto.getStatus() == fornecedorToUpdate.getStatus()) {
            throw new RuntimeException("Fornecedor já possui esse status");
        }
        fornecedorToUpdate.setStatus(fornecedorDto.getStatus());
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorRepository.save(fornecedorToUpdate), FornecedorDto.class);
        return fornecedorReturn;
    }

    public List<FornecedorDto> getAllFornecedores(String username) {
        if (simpleRules.verifyString(username)) {
            throw new RuntimeException("string vazia");
        }
        List<Fornecedor> forne = fornecedorRepository.findFornecedorByUsername(username);
        List<FornecedorDto> fornecedores = mapper(forne);
       return fornecedores;
    }

    private List<FornecedorDto> mapper(List<Fornecedor> fornecedors) {
        List<FornecedorDto> fornecedorDto = fornecedors.stream().map(a -> new FornecedorDto(a.getRazaoSocial(), a.getCnpj(), a.getStatus())).collect(Collectors.toList());
        return fornecedorDto;
    }
}
