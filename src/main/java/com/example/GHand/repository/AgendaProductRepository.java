package com.example.GHand.repository;

import com.example.GHand.document.agenda.AgendaProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaProductRepository extends MongoRepository<AgendaProduct, String> {


}
