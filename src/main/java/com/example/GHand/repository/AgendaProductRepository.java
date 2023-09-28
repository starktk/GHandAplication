package com.example.GHand.repository;

import com.example.GHand.document.fornecedor.product.AgendaProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AgendaProductRepository extends MongoRepository<AgendaProduct, String> {


}
