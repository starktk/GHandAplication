package com.example.GHand.repository;


import com.example.GHand.document.fornecedor.Fornecedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FornecedorRepository  extends MongoRepository<Fornecedor, String> {

    @Query("{'username' : :#{#username}}")
    List<Fornecedor> findFornecedorByUsername(@Param("username") String username);

    @Query("{'razaoSocial' : :#{#razaoSocial}, 'username' : :#{#username}}")
    Fornecedor findFornecedorByrazaoSocialAndUsername(@Param("razaoSocial") String razaoSocial, @Param("username") String username);


}
