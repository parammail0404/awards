package com.myapp;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import com.myapp.model.Customer;
import com.myapp.repository.CustomerRepository;

@SpringBootApplication
public class MainApplication {

	private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Autowired    
	CustomerRepository customerRepository;

	// Run this if app.db.init.enabled = true
	@Bean
	@ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
	public CommandLineRunner demoCommandLineRunner() {
		return args -> {

			System.out.println("Running.....");

			Customer b1 = new Customer("User1",100, LocalDate.of(2024, 6, 1));
			Customer b2 = new Customer("User1", 60, LocalDate.of(2024, 7, 12));
			Customer b3 = new Customer("User1", 120, LocalDate.of(2024, 8, 13));
			Customer b4 = new Customer("User1", 140, LocalDate.of(2024, 9, 15));


			Customer b5 = new Customer("User2", 55, LocalDate.of(2024, 6, 01));
			Customer b6 = new Customer("User2", 110, LocalDate.of(2024, 6, 05));
			Customer b7 = new Customer("User2", 220, LocalDate.of(2024, 8, 10));
			Customer b8 = new Customer("User2", 320, LocalDate.of(2024, 9, 25)); 
			

			Customer b9 = new  Customer("User3", 150, LocalDate.of(2024, 6, 01));
			Customer b10 = new Customer("User3", 250, LocalDate.of(2024, 7, 05));
			Customer b11 = new Customer("User3", 50, LocalDate.of(2024, 8, 10));
			Customer b12 = new Customer("User3", 50, LocalDate.of(2024, 9, 25));




			customerRepository.saveAll(List.of(b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12));
			log.info("Default records inserted ");
		};
	}

}