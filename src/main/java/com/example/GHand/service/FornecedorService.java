package com.example.GHand.service;

import com.example.GHand.document.fornecedor.Fornecedor;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.fornecedor.FornecedorRequestDto;
import com.example.GHand.exceptions.NotFoundException;
import com.example.GHand.exceptions.ValueNotAcceptedException;
import com.example.GHand.repository.FornecedorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    private final UsuarioService usuarioService;
    public final ObjectMapper objectMapper;

    public FornecedorDto addFornecedor(FornecedorRequestDto fornecedorRequestDto) throws ValueNotAcceptedException, NotFoundException {
        if (fornecedorRequestDto.getRazaoSocial().isEmpty()) {
            throw new ValueNotAcceptedException("Razão Social inválida");
        }
        if (fornecedorRepository.findFornecedorByrazaoSocialAndUsername(fornecedorRequestDto.getRazaoSocial(), fornecedorRequestDto.getUsername()) != null) {
            throw new ValueNotAcceptedException("Fornecedor já existente");
        }
        if (!usuarioService.verifyUser(fornecedorRequestDto.getUsername())) {
            throw new NotFoundException("Usuario não encontrado!!");
        }
        if (fornecedorRequestDto.getCnpj().length() != 11) {
            throw new ValueNotAcceptedException("Cnpj Inválido");
        }
        Fornecedor fornecedorSave = objectMapper.convertValue(fornecedorRequestDto, Fornecedor.class);
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorRepository
                .save(fornecedorSave), FornecedorDto.class);
        return fornecedorReturn;
    }

    public FornecedorDto findFornecedor(FornecedorRequestDto fornecedorDto) throws ValueNotAcceptedException, NotFoundException {
        if(fornecedorDto.getRazaoSocial().isEmpty()) {
            throw new ValueNotAcceptedException("Razão social inválida!!");
        } else if (fornecedorRepository.findFornecedorByrazaoSocialAndUsername(fornecedorDto.getRazaoSocial(), fornecedorDto.getUsername()) == null) {
            throw new NotFoundException("Fornecedor não encontrado");
        }
        Fornecedor fornecedorToFind = fornecedorRepository.findFornecedorByrazaoSocialAndUsername(fornecedorDto.getRazaoSocial(), fornecedorDto.getUsername());
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorToFind, FornecedorDto.class);
        return fornecedorReturn;
    }
    public FornecedorDto alterFornecedor(FornecedorRequestDto fornecedorRequestDto) throws ValueNotAcceptedException, NotFoundException {
        if (!usuarioService.verifyUser(fornecedorRequestDto.getUsername())) {
            throw new NotFoundException("Usuario não encontrado!!");
        }
        if (fornecedorRequestDto.getRazaoSocial().isEmpty()) {
            throw new ValueNotAcceptedException("Razão social inválida!!");
        } else if (!fornecedorRepository.findById(fornecedorRequestDto.getRazaoSocial()).isPresent()) {
            throw new NotFoundException("Fornecedor não encontrado");
        } else if (fornecedorRequestDto.getCnpj().length() != 11) {
            throw new ValueNotAcceptedException("Cnpj inválido!!");
        }

        Fornecedor fornecedorToSave = fornecedorRepository.findFornecedorByrazaoSocialAndUsername(fornecedorRequestDto.getRazaoSocial(), fornecedorRequestDto.getUsername());
        switch (fornecedorRequestDto.getStatus()) {
            case ATIVA, INATIVA -> fornecedorToSave.setStatus(fornecedorRequestDto.getStatus());
            default -> throw new ValueNotAcceptedException("Status inválido!!");
        }
        fornecedorToSave.setRazaoSocial(fornecedorRequestDto.getRazaoSocial());
        fornecedorToSave.setCnpj(fornecedorRequestDto.getCnpj());
        FornecedorDto fornecedorToReturn = objectMapper.convertValue(fornecedorRepository.save(fornecedorToSave), FornecedorDto.class);
        return fornecedorToReturn;
    }

    public void deleteFornecedor(String razaoSocial) throws ValueNotAcceptedException, NotFoundException {
        if (razaoSocial.isEmpty()) {
            throw new ValueNotAcceptedException("Razão social inválida!!");
        } else if (!fornecedorRepository.findById(razaoSocial).isPresent()) {
            throw new NotFoundException("Fornecedor não encontrado");
        }
        fornecedorRepository.deleteById(razaoSocial);
    }
    public ResponseEntity<FornecedorDto> changeStatus(FornecedorDto fornecedorDto) {
        if(fornecedorDto.getRazaoSocial().isEmpty()) {
            return new ResponseEntity("Razão Social inválida", HttpStatus.NOT_ACCEPTABLE);
        }
        Fornecedor fornecedorToUpdate = objectMapper.convertValue(fornecedorRepository.findById(fornecedorDto.getRazaoSocial()), Fornecedor.class);
        if (fornecedorDto.getStatus() == fornecedorToUpdate.getStatus()) {
            return new ResponseEntity("Fornecedor já possui esse status", HttpStatus.NOT_ACCEPTABLE);
        }
        fornecedorToUpdate.setStatus(fornecedorDto.getStatus());
        FornecedorDto fornecedorReturn = objectMapper.convertValue(fornecedorRepository.save(fornecedorToUpdate), FornecedorDto.class);
        return new ResponseEntity(fornecedorReturn, HttpStatus.ACCEPTED);
    }

    public List<FornecedorDto> getAllFornecedores(String username) throws ValueNotAcceptedException, NotFoundException {
        if (username.isEmpty()) {
            throw new ValueNotAcceptedException("Razão social inválida!!");
        } else if (!usuarioService.verifyUser(username)) {
            throw new NotFoundException("Usuário inexistente!!");
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
