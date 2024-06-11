package com.awards.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "com.awards.customer")
@EnableJpaRepositories(basePackages = "com.awards.customer.repository")

@EntityScan("com.awards.customer.model")

public class AwardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwardsApplication.class, args);
	}

}
