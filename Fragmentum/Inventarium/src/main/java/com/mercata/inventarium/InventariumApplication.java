package com.mercata.inventarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.mercata.inventarium.Repository")
public class InventariumApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventariumApplication.class, args);
	}

}
