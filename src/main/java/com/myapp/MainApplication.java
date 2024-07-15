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

			Customer a1 = new Customer("User1",100, LocalDate.of(2024, 6, 1));
			Customer a2 = new Customer("User1", 60, LocalDate.of(2024, 7, 12));
			Customer a3 = new Customer("User1", 120, LocalDate.of(2024, 7, 13));
			Customer a4 = new Customer("User1", 140, LocalDate.of(2024, 9, 15));
			Customer a5 = new Customer("User1", 156, LocalDate.of(2024, 9, 15));
			customerRepository.saveAll(List.of(a1, a2, a3, a4, a5));

			Customer b1 = new Customer("User2", 55, LocalDate.of(2024, 6, 01));
			Customer b2 = new Customer("User2", 110, LocalDate.of(2024, 6, 05));
			Customer b3 = new Customer("User2", 220, LocalDate.of(2024, 8, 10));
			Customer b4 = new Customer("User2", 320, LocalDate.of(2024, 9, 25)); 
			Customer b5 = new Customer("User2", 80, LocalDate.of(2024, 9, 25));
			customerRepository.saveAll(List.of(b1, b2, b3, b4, b5));

			Customer c1 = new  Customer("User3", 150, LocalDate.of(2024, 6, 01));
			Customer c2 = new Customer("User3", 250, LocalDate.of(2024, 7, 05));
			Customer c3 = new Customer("User3", 50, LocalDate.of(2024, 8, 10));
			Customer c4 = new Customer("User3", 5, LocalDate.of(2024, 9, 25));
			Customer c5 = new Customer("User3", 250, LocalDate.of(2024, 9, 25));
			customerRepository.saveAll(List.of(c1, c2, c3, c4, c5));
			
			log.info("Default records inserted ");
		};
	}

}