package com.myapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private Integer orderAmount;
	private LocalDate orderDate;
	private String month;
	private Long award =0l;


	public Customer(String name, Integer orderAmount, LocalDate orderDate) {
		this.name = name;
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", orderAmount=" + orderAmount +
				", orderDate=" + orderDate +
				", month=" + month +
				'}';
	}
	//To get Month name 
	public String getMonth() {
		if(orderDate != null)
			return orderDate.getMonth().name();
		else
			return "";	
	}    
}
