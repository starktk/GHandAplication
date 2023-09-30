package com.example.GHand.repository;

import com.example.GHand.document.usuario.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {


    @Query("{'username' : :#{#username}}")
    Usuario findUsuarioByUsername(@Param("username") String username);
}
