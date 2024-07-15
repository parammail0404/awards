package com.myapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.model.Customer;

// Spring Data JPA creates CRUD implementation at runtime automatically.
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByName(String name);

    // Custom query
    @Query("SELECT b FROM Customer b WHERE b.orderDate > :date")
    List<Customer> findByDateAfter(@Param("date") LocalDate date);


}

