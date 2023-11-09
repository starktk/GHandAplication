package com.example.GHand.repository;

import com.example.GHand.document.agenda.Agenda;
import com.example.GHand.document.agenda.AgendaProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AgendaProductRepository extends MongoRepository<AgendaProduct, String> {

    @Query("{'razaoSocial' : :#{#razaoSocial}, 'username' : :#{#username}}")
    AgendaProduct findAgendaProductByrazaoSocialAndusername(@Param("razaoSocial") String razaoSocial, @Param("username") String username);

    @Query("{'razaoSocial' : :#{razaoSocial}, 'markDate' : :#{markDate}}")
    List<AgendaProduct> findAllByrazaoSocialAndmarkDate(@Param(("razaoSocial")) String razaoSocial, @Param("markDate") LocalDate date);
}
