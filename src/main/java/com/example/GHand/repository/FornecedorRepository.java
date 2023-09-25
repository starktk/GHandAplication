package com.example.GHand.repository;

import com.example.GHand.document.fornecedor.Fornecedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface FornecedorRepository  extends MongoRepository<Fornecedor, String> {

//    @Query(value = "{'username' : :#{#username}}")
//    List<Fornecedor> findAllByUsername(@Param("username") String username);

    @Query("{'username' : :#{#username}}")
    List<Fornecedor> findFornecedorByUsername(@Param("username") String username);
}
