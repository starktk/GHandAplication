package com.example.GHand.repository;

import com.example.GHand.document.fornecedor.Fornecedor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FornecedorRepository  extends MongoRepository<Fornecedor, String> {


}
