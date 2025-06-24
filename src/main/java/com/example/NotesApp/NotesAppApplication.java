package com.example.NotesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.example.NotesApp.repository")
@EntityScan(basePackages = "com.example.NotesApp.model")
public class NotesAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotesAppApplication.class, args);
	}
}
