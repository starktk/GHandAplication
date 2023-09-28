package com.example.GHand.repository;

import com.example.GHand.document.agenda.AgendaPayment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaPaymentRepository extends MongoRepository<AgendaPayment, String> {


}
