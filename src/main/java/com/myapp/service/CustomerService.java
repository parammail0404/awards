package com.myapp.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exceptions.NoRecordFoundException;
import com.myapp.model.Customer;
import com.myapp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> findAll() {

		List<Customer> customerDtoList =customerRepository.findAll();
		if(customerDtoList!=null || customerDtoList.size()==0) {
			throw new NoRecordFoundException();
		}
		return customerDtoList;
	}

	public Map<String, Object> getPointsReport() {

		Map<String, Object> returnMap = new HashMap<String, Object>(); 


		List<Customer> customerDtoList =   findAll();
		if(customerDtoList!=null || customerDtoList.size()==0) {
			throw new NoRecordFoundException();
		}


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
						awards = ordersAmount-50;
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
