package com.awards.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.awards.customer.model.Customer;


@Repository
public interface CustomerRpository extends JpaRepository<Customer, Integer> {

	@Query("from customer")
	public List<Customer> getCustomers();
}

