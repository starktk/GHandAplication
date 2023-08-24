package com.example.ABC.repository;

import com.example.ABC.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {


}
