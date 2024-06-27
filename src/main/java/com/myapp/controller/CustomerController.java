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
    	log.info("");
    	return customerkService.getPointsReport();
    }
    @GetMapping("/all")
    public List<Customer> findAll() {
    	
    	
    	List<Customer> customerDtoList =   customerkService.findAll();
    	return customerDtoList;
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable Long id) {
        return customerkService.findById(id);
    }

    // create a customer
    @ResponseStatus(HttpStatus.CREATED) // 201
    @PostMapping
    public Customer create(@RequestBody Customer customer) {
        return customerkService.save(customer);
    }

    // update a customer
    @PutMapping
    public Customer update(@RequestBody Customer customer) {
        return customerkService.save(customer);
    }

    // delete a customer
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        customerkService.deleteById(id);
    }

    @GetMapping("/find/title/{title}")
    public List<Customer> findByTitle(@PathVariable String title) {
        return customerkService.findByName(title);
    }

    @GetMapping("/find/date-after/{date}")
    public List<Customer> findByOrderDateAfter(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return customerkService.findBydDateAfter(date);
    }

    
    @ExceptionHandler(NoRecordFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleNoRecordFoundException(NoRecordFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage("No Record Found !");
        return errorResponse;
    }
    
    
    
}
