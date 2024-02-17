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
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<FornecedorDto> findFornecedorByrazaoSocial(FornecedorRequestDto fornecedorRequestDto) throws NotFoundException, InvalidValueException {
        List<FornecedorDto> fornecedores = findAllFornecedores(fornecedorRequestDto.getUsername());
        if (fornecedorRequestDto.getRazaoSocial().isBlank()) throw new InvalidValueException("Preencha o campo!");
        if (fornecedores.stream().noneMatch(forne -> forne.getRazaoSocial().equals(fornecedorRequestDto.getRazaoSocial()))) throw new NotFoundException("Fornecedores não encontrados");
        List<FornecedorDto> fornecedorReturn = fornecedores.stream().filter(fornecedor -> fornecedor.getRazaoSocial().equals(fornecedorRequestDto.getRazaoSocial())).toList();
        return fornecedorReturn;
    }

    public FornecedorDto getFornecedorByCnpj(FornecedorRequestDto fornecedorRequestDto) throws InvalidValueException, NotFoundException {
        if (fornecedorRequestDto.getCnpj().isBlank() || fornecedorRequestDto.getCnpj().length() <= 11) throw new InvalidValueException("Cnpj inválido");
        List<FornecedorDto> fornecedores = findAllFornecedores(fornecedorRequestDto.getUsername());
        if (fornecedores.stream().noneMatch(forne -> forne.getCnpj().equals(fornecedorRequestDto.getCnpj()))) throw new NotFoundException("Fornecedor não encontrado");
        Stream<FornecedorDto> fornecedorDto = fornecedores.stream()
                .filter(fornecedor -> fornecedor.getCnpj().equals(fornecedorRequestDto.getCnpj()));
        FornecedorDto fornecedorReturn = fornecedorDto.findAny().get();

        return fornecedorReturn;
    }



    public List<FornecedorDto> findByStatus(FornecedorRequestDto fornecedorRequestDto) throws InvalidValueException, NotFoundException {
        if (fornecedorRequestDto.getStatus() != Situacao.ATIVA || fornecedorRequestDto.getStatus() != Situacao.INATIVA) throw new InvalidValueException("Status inválido");
        List<FornecedorDto> fornecedores = findAllFornecedores(fornecedorRequestDto.getUsername());
        List<FornecedorDto> fornecedoresReturn = fornecedores
                .stream().filter(fornecedor -> fornecedor.getStatus()
                        .equals(fornecedorRequestDto.getStatus())).toList();
        return fornecedoresReturn;
    }
}
