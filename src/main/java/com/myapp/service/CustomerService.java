package com.myapp.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.myapp.exceptions.NoRecordFoundException;
import com.myapp.model.Customer;
import com.myapp.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Value("${customer.calcu.ld100}")
    private Long ld100;
	
	@Value("${customer.calcu.gd100}")
    private Long gd100;
	
	@Value("${customer.calcu.limit1}")
    private Long limit1;
	
	@Value("${customer.calcu.limit2}")
    private Long limit2;
	
	public List<Customer> findAll() {

		List<Customer> customerDtoList =customerRepository.findAll();
		if(customerDtoList==null || customerDtoList.size()==0) {
			throw new NoRecordFoundException();
		}
		customerDtoList.forEach(customer -> customer.setAward(calculateAwards(Long.valueOf(customer.getOrderAmount().longValue()))));
		return customerDtoList;
	}

	public Map<String, Object> getPointsReport() {

		Map<String, Object> returnMap = new HashMap<String, Object>(); 
		
		

		List<Customer> customerDtoList =   findAll();
		if(customerDtoList==null || customerDtoList.size()==0) {
			throw new NoRecordFoundException();
		}
		customerDtoList.forEach(customer -> customer.setAward(calculateAwards(Long.valueOf(customer.getOrderAmount().longValue()))));
//		calculateAwards(ordersAmount)  Long.valueOf(i.longValue())

		Map<String, List<Customer>> ordersByUsers = customerDtoList.stream().collect(Collectors.groupingBy(Customer::getName));
		
		//		returnMap.put("ordersByUserWiseList", ordersByUsers);

		Iterator<String> iterator2 = ordersByUsers.keySet().iterator();
		Map<String, List<Customer>> ordersByUsersMonthly;
		HashMap<String, Object> mapList= new HashMap<String, Object>();		

//		while (iterator2.hasNext()) {
//			String userName = iterator2.next();
//			ordersByUsersMonthly =  ordersByUsers.get(userName).stream().collect(Collectors.groupingBy(Customer::getMonth));			
//			mapList.put(userName, ordersByUsersMonthly);
//		}


		Iterator<String> iterator3 = ordersByUsers.keySet().iterator();
		HashMap<String, Object> mapList2= new HashMap<String, Object>();
		
		while (iterator3.hasNext()) {
			String userName = iterator3.next();
			ordersByUsersMonthly =  ordersByUsers.get(userName).stream().collect(Collectors.groupingBy(Customer::getMonth));
			Iterator<String> iterator4 = ordersByUsersMonthly.keySet().iterator();
			Map<String, Long> map4 = new HashMap<String, Long>();
			HashMap<String, Object> mapList4= new HashMap<String, Object>();
			while (iterator4.hasNext()) {
				String month = iterator4.next();
				map4 =ordersByUsersMonthly.get(month).stream().collect(Collectors.groupingBy(Customer::getMonth, Collectors.summingLong(Customer::getOrderAmount)));

				Iterator<String> iterator5 = map4.keySet().iterator();
				
				
				while (iterator5.hasNext()) {
					String monthName = iterator5.next();
					Long ordersAmount = map4.get(monthName);
					
					map4.put("Points", calculateAwards(ordersAmount));
				}				
				mapList4.put(month, map4);				
			}
			mapList2.put(userName, mapList4);
		}
		returnMap.put("OrderDetails", customerDtoList);
		returnMap.put("TotalOrderAmtPoint", mapList2);

		return returnMap;
	}

	public Optional<Customer> findById(Long id) {
		Optional <Customer> customerOptional =customerRepository.findById(id);
		if(!customerOptional.isPresent()) {
			throw new NoRecordFoundException();
		}
		return customerOptional;
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
	public Long calculateAwards(Long orderAmount) {
		Long awards=0l;
		
		if(orderAmount<limit1)
			awards = 0l;
		else if(orderAmount>50 && orderAmount <=limit2) {
			awards = ((orderAmount-50) *ld100);
		}
		else if(orderAmount >limit2) {			
			awards =((gd100*(orderAmount-limit2))+limit1);
		}
		return awards;
	}
}
