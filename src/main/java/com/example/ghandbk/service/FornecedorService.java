package com.example.ghandbk.service;

import com.example.ghandbk.collection.enums.Situacao;
import com.example.ghandbk.collection.supplier.Fornecedor;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import com.example.ghandbk.dto.supllier.FornecedorRequestDto;
import com.example.ghandbk.dto.user.UsuarioRequestDto;
import com.example.ghandbk.exceptions.InvalidValueException;
import com.example.ghandbk.exceptions.NotAuthorizedException;
import com.example.ghandbk.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FornecedorService {

    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public Fornecedor addFornecedor(FornecedorRequestDto fornecedorRequestDto) throws InvalidValueException, NotAuthorizedException, NotFoundException {
        if (fornecedorRequestDto.getRazaoSocial().isBlank()) throw new InvalidValueException("Preencha o campo");
        if (fornecedorRequestDto.getCnpj().length() <= 11) throw new InvalidValueException("Cnpj inválido");
        if (fornecedorRequestDto.getStatus().equals(Situacao.INATIVA)) throw new InvalidValueException("Status inválido");
        UsuarioRequestDto usuarioRequestDto = new UsuarioRequestDto();
        usuarioRequestDto.setUsername(fornecedorRequestDto.getUsername());
        usuarioRequestDto.setName(fornecedorRequestDto.getName());
        Fornecedor fornecedor = objectMapper.convertValue(fornecedorRequestDto, Fornecedor.class);
        usuarioRequestDto.setFornecedor(fornecedor);
        usuarioService.updateUser(usuarioRequestDto);
        return fornecedor;
    }

    public List<FornecedorDto> findAllFornecedores(String username) throws InvalidValueException, NotFoundException {
       List<Fornecedor> fornecedores = usuarioService.getFornecedores(username);
       List<FornecedorDto> fornecedorDtos = fornecedores.stream().map(a -> new FornecedorDto(a.getRazaoSocial(), a.getCnpj(), a.getStatus())).collect(Collectors.toList());
       return fornecedorDtos;
    }
//
//    public List<FornecedorDto> findFornecedorByrazaoSocial(FornecedorRequestDto fornecedorRequestDto) throws NotFoundException, InvalidValueException {
//        Usuario usuario = validateUser(fornecedorRequestDto);
//        if (usuario.getFornecedores().isEmpty()) throw new NotFoundException("Fornecedor não encontrado");
//        if (fornecedorRequestDto.getRazaoSocial().isBlank()) throw new InvalidValueException("Preencha o campo!");
//        List<FornecedorDto> fornecedores = usuario.getFornecedores().stream().filter(fornecedor -> fornecedor.getRazaoSocial().contains(fornecedorRequestDto.getRazaoSocial())).map(fornecedor -> new FornecedorDto(fornecedor.getRazaoSocial(), fornecedor.getCnpj(), fornecedor.getStatus())).toList();
//        return fornecedores;
//    }
}
