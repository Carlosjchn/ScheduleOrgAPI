package com.jpa1prueba.jpademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jpa1prueba.jpademo", "com.jpa1prueba.existdbmodule", "com.jpa1prueba.mongodbmodule"})
@EnableMongoRepositories(basePackages = "com.jpa1prueba.mongodbmodule.repositories")
public class JpademoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpademoApplication.class, args);
	}

}
