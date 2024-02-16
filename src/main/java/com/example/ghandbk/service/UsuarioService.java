package com.example.ghandbk.service;



import com.example.ghandbk.collection.supplier.Fornecedor;
import com.example.ghandbk.collection.user.Usuario;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import com.example.ghandbk.dto.user.UsuarioDto;
import com.example.ghandbk.dto.user.UsuarioRequestDto;
import com.example.ghandbk.exceptions.InvalidValueException;
import com.example.ghandbk.exceptions.NotAuthorizedException;
import com.example.ghandbk.exceptions.NotFoundException;
import com.example.ghandbk.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepo;

    public UsuarioDto insertUser(UsuarioRequestDto usuarioRequestDto) throws InvalidValueException, NotAuthorizedException {
        if(usuarioRepo.existsById(usuarioRequestDto.getUsername())) throw new NotAuthorizedException("Usuário já existente");
        if (usuarioRequestDto.getUsername().isBlank()) throw new InvalidValueException("Preencha o campo!!");
        if (usuarioRequestDto.getName().isBlank()) throw new InvalidValueException("Preencha o campo!!");
        if (usuarioRequestDto.getPassword().isBlank() || usuarioRequestDto.getPassword().length() <= 4) throw new InvalidValueException("Senha inválida");
        Usuario userToSave = objectMapper.convertValue(usuarioRequestDto, Usuario.class);
        return objectMapper.convertValue(usuarioRepo.save(userToSave), UsuarioDto.class);
    }

    public void deleteUser(UsuarioRequestDto usuarioRequestDto) throws InvalidValueException, NotFoundException, NotAuthorizedException {
        if (usuarioRequestDto.getUsername().isBlank() && usuarioRequestDto.getPassword().isBlank()) throw new InvalidValueException("Preencha os campos");
        if (usuarioRepo.existsById(usuarioRequestDto.getUsername())) throw new NotFoundException("Usuário não encontrado");
        if (usuarioRepo.findUser(usuarioRequestDto.getUsername(), usuarioRequestDto.getPassword()) == null) throw new NotAuthorizedException("Usuário inválido para deletar");
        usuarioRepo.delete(usuarioRepo.findUser(usuarioRequestDto.getUsername(), usuarioRequestDto.getPassword()));
    }

    public Usuario updateUser(UsuarioRequestDto usuarioRequestDto) throws InvalidValueException, NotAuthorizedException {
        if (!usuarioRepo.existsById(usuarioRequestDto.getUsername())) throw new NotAuthorizedException("Usuário inválido");
        Usuario user = usuarioRepo.findById(usuarioRequestDto.getUsername()).get();
        if (usuarioRequestDto.getUsername().isBlank()) throw new InvalidValueException("Username inválido");
        if (usuarioRequestDto.getName().isBlank()) throw new InvalidValueException("Nome inválido");
        try {
            if (!user.getFornecedores().isEmpty()) {
                user.getFornecedores().add(usuarioRequestDto.getFornecedor());
            }
        } catch (NullPointerException exception) {
            ArrayList<Fornecedor> fornecedors = new ArrayList<>();
            fornecedors.add(usuarioRequestDto.getFornecedor());
            user.setFornecedores(fornecedors);
        }
        user.setUsername(usuarioRequestDto.getUsername());
        user.setName(usuarioRequestDto.getName());
        usuarioRepo.save(user);
        return usuarioRepo.findById(usuarioRequestDto.getUsername()).get();
    }

    public UsuarioDto findUser(String username) throws  InvalidValueException, NotFoundException {
        if (username.isBlank()) throw new InvalidValueException("Username inválido");
        if (!usuarioRepo.existsById(username)) throw new NotFoundException("Usuário não encontrado");
        Usuario usuario = usuarioRepo.findById(username).get();
        UsuarioDto userReturn = objectMapper.convertValue(usuario, UsuarioDto.class);
        try {
            List<FornecedorDto> fornecedoresToReturn = usuario.getFornecedores().stream()
                    .map(a -> new FornecedorDto
                            (a.getRazaoSocial(), a.getCnpj(), a.getStatus())).collect(Collectors.toList());
            userReturn.setFornecedoresDto(fornecedoresToReturn);
        } catch (NullPointerException e) {
            return userReturn;
        }
        return userReturn;
    }
    public Usuario findUserByid(String username) throws NotFoundException, InvalidValueException {
        if (username.isBlank()) throw new InvalidValueException("Username inválido");
        if (!usuarioRepo.existsById(username)) throw new NotFoundException("Usuário não encontrado");
        return usuarioRepo.findById(username).get();
    }

    public List<Fornecedor> getFornecedores(String username) throws InvalidValueException, NotFoundException {
        if (username.isBlank()) throw new InvalidValueException("Username inválido");
        if (!usuarioRepo.existsById(username)) throw new NotFoundException("Usuário não encontrado");
        Usuario usuario = usuarioRepo.findById(username).get();
        if (usuario.getFornecedores().isEmpty()) throw new NotFoundException("Fornecedores não encontrados");
        return usuario.getFornecedores();
    }
}
