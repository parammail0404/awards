package com.myapp.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.model.Customer;
import com.myapp.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
   

    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    public List<Customer> findByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<Customer> findBydDateAfter(LocalDate date) {
        return customerRepository.findByDateAfter(date);
    }
}
