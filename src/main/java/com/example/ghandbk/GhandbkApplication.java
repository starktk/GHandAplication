package com.example.ghandbk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class GhandbkApplication {

	public static void main(String[] args) {
		SpringApplication.run(GhandbkApplication.class, args);
	}

}
