package com.myapp.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.MainApplication;
import com.myapp.exceptions.ErrorResponse;
import com.myapp.exceptions.NoRecordFoundException;
import com.myapp.model.Customer;
import com.myapp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerkService;
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    
    
    
    
    @GetMapping("/pointsReport")
    public Map<String, Object> getPointsReport() {
    	log.info("Getting Point reports end point..");
    	return customerkService.getPointsReport();
    }
    @GetMapping("/all")
    public List<Customer> findAll() {
    	
    	log.info("Getting All customers details..");
    	List<Customer> customerList =   customerkService.findAll();
    	log.info("Getting All customers details, customerList Size : .."+customerList.size());
    	return customerList;
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable Long id) {
    	log.info("Getting customers details for the ID "+id);
        return customerkService.findById(id);
    }

    // create a customer
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
    	log.info("In inserting customers details, Customer Nam  "+customer.getName());
        return customerkService.save(customer);
    }

    // update a customer
    @PutMapping
    public Customer update(@RequestBody Customer customer) {
    	log.info("In updating customers details, Customer Nam  "+customer.getName());
        return customerkService.save(customer);
    }

    // delete a customer
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
    	log.info("In Deleting  customers details, Customer id  "+id);
        customerkService.deleteById(id);
    }

    @GetMapping("/find/title/{name}")
    public List<Customer> findByTitle(@PathVariable String name) {
    	log.info("In Finding customers details by Name , Customer Name "+name);
        return customerkService.findByName(name);
    }

    @GetMapping("/find/date-after/{date}")
    public List<Customer> findByOrderDateAfter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
    	log.info("In fet customers details after the order date, Date"+date);
        return customerkService.findBydDateAfter(date);
    }

    
	/*
	 * @ExceptionHandler(NoRecordFoundException.class)
	 * 
	 * @ResponseStatus(HttpStatus.NOT_FOUND)
	 * 
	 * @ResponseBody public ErrorResponse
	 * handleNoRecordFoundException(NoRecordFoundException ex) {
	 * 
	 * ErrorResponse errorResponse = new ErrorResponse();
	 * errorResponse.setMessage("No Record Found !"); return errorResponse; }
	 */
    
    
}
