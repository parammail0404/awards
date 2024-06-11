package com.awards.customer.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.awards.customer.dto.CustomerDto;
import com.awards.customer.model.Customer;
import com.awards.customer.service.CustomersService;

@RestController
@RequestMapping("/awards")
public class AwardsController {

	private final CustomersService customerService;

	public AwardsController(CustomersService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping("/")
	public String home() 
	{
		return "It working !";
	}
	@RequestMapping("/Home")
	public String hello() 
	{
		return "Welcome to Awards Home !";
	}
	@PostMapping(value = "/addSale")
	public String createUser() {

		return "addSale";
	}
	@PostMapping("/addOrders")
	public void add(@RequestBody CustomerDto customerDto){
		customerDto.setDate(new Date());
		customerService.add(customerDto);
	}

	@GetMapping("/getOrders")
	public Map<String, Object>  getAll(){
		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		List<Customer> customerDtoList =   customerService.getAll();


	//get month wise orders list(like month and orders list)
		Map<String, List<Customer>> ordersByMonth = customerDtoList.stream().collect(Collectors.groupingBy(Customer::getMonth));

		returnMap.put("ordersByMonthWiseList", ordersByMonth);
	//to get the month wise sum of orders amount
		Map<String, Integer> totalOrderValueMonthWise = customerDtoList.stream().collect(Collectors.groupingBy(Customer::getMonth, Collectors.summingInt(Customer::getAmount)));
		returnMap.put("monthWiseTotalOrdersMap", new ArrayList().add(totalOrderValueMonthWise));

	//Calculating the month wise points/awards
		Integer awards = 0;
		Integer ordersAmount = 0;
		Map<String, Integer> totalAwardsMonthWise =new HashMap<String, Integer>(); 
		Iterator<String> iterator = totalOrderValueMonthWise.keySet().iterator();
		while (iterator.hasNext()) {
			awards = 0;
			ordersAmount = 0;
			String key = iterator.next();
			ordersAmount = totalOrderValueMonthWise.get(key);

			if(ordersAmount<50)
				awards = 0;
			else if(ordersAmount>50 && ordersAmount <=100)
				awards = 50;
			else if(ordersAmount >100)
				awards =(2*(ordersAmount-100))+50;
			totalAwardsMonthWise.put(key, awards);

		}
		returnMap.put("monthWiseAwardsMap", new ArrayList().add(totalAwardsMonthWise));

		return returnMap;


	}
}
