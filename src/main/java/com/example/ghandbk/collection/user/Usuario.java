package com.example.ghandbk.collection.user;

import com.example.ghandbk.collection.supplier.Fornecedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Usuario")
public class Usuario {

    @Id
    private String username;
    private String name;
    private String password;
    private ArrayList<Fornecedor> fornecedores;

}
