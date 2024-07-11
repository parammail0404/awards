package com.myapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer orderAmount;
    private LocalDate orderDate;
    private String month;
    private Long award =0l;
    // for JPA only, no use
    public Customer() {
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public String getMonth() {
		return orderDate.getMonth().name();
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getAward() {
		
		return award;
	}

	public void setAward(Long award) {
		this.award = award;
	}


    
}
