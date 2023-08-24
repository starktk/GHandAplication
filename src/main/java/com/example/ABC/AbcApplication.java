package com.example.ABC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class AbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcApplication.class, args);
	}

}
