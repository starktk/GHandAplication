package com.example.GHand.repository;

import com.example.GHand.document.agenda.Agenda;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AgendaRepository extends MongoRepository<Agenda, String> {


}
