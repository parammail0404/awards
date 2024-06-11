package com.awards.customer.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.awards.customer.dto.CustomerDto;
import com.awards.customer.mapper.CustomerMapper;
import com.awards.customer.model.Customer;
import com.awards.customer.repository.CustomerRpository;

@Service
public class CustomersService {

	private final CustomerRpository customerRepository;
	private final CustomerMapper customerMapper;
	

	public CustomersService(CustomerRpository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}
	public void add(CustomerDto suburbDto) {
		System.out.println(suburbDto);
		customerRepository.save(customerMapper.toEntity(suburbDto));
	}

	public List<Customer> getAll() {
		List<Customer> customerList = customerRepository.findAll();
		

		

		return customerList ;
	}
	
	
}
