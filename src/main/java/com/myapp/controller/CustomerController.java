package com.myapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.model.Customer;
import com.myapp.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerkService;
    
    @GetMapping("/all")
    public Map<String, Object> findAll() {
    	
    	Map<String, Object> returnMap = new HashMap<String, Object>(); 
		List<Customer> customerDtoList =   customerkService.findAll();

		
		Map<String, List<Customer>> ordersByUsers = customerDtoList.stream().collect(Collectors.groupingBy(Customer::getName));
//		returnMap.put("ordersByUserWiseList", ordersByUsers);
		
		Iterator<String> iterator2 = ordersByUsers.keySet().iterator();
		Map<String, List<Customer>> ordersByUsersMonthly;
		HashMap<String, Object> mapList= new HashMap<String, Object>();		
		
		while (iterator2.hasNext()) {
			String userName = iterator2.next();
			ordersByUsersMonthly =  ordersByUsers.get(userName).stream().collect(Collectors.groupingBy(Customer::getMonth));			
			mapList.put(userName, ordersByUsersMonthly);
		}
		
		
		Iterator<String> iterator3 = ordersByUsers.keySet().iterator();
		HashMap<String, Object> mapList2= new HashMap<String, Object>();
		Integer awards=0;
		while (iterator3.hasNext()) {
			String userName = iterator3.next();
			ordersByUsersMonthly =  ordersByUsers.get(userName).stream().collect(Collectors.groupingBy(Customer::getMonth));
			Iterator<String> iterator4 = ordersByUsersMonthly.keySet().iterator();
			Map<String, Integer> map4 = new HashMap<String, Integer>();
			HashMap<String, Object> mapList4= new HashMap<String, Object>();
			while (iterator4.hasNext()) {
				String month = iterator4.next();
				map4 =ordersByUsersMonthly.get(month).stream().collect(Collectors.groupingBy(Customer::getMonth, Collectors.summingInt(Customer::getOrderAmount)));
				
				Iterator<String> iterator5 = map4.keySet().iterator();
				while (iterator5.hasNext()) {
					String monthName = iterator5.next();
					Integer ordersAmount = map4.get(monthName);
					awards=0;
					if(ordersAmount<50)
						awards = 0;
					else if(ordersAmount>50 && ordersAmount <=100)
						awards = 50;
					else if(ordersAmount >100)
						awards =(2*(ordersAmount-100))+50;
					
					map4.put("Points", awards);
				}				
				mapList4.put(month, map4);				
			}
			mapList2.put(userName, mapList4);
		}
		returnMap.put("OrderDetails", mapList);
		returnMap.put("TotalOrderAmtPoint", mapList2);
		
    	
    	
    	
    	
        return returnMap;
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

}
