package com.example.GHand.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Usuario")
public class Usuario {


    @Id
    private String username;
    private String name;
    private String password;

}
