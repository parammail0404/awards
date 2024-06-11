package com.awards.customer.dto;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
  private Date date;
  private Integer amount;
  private String month;


public Date getDate() {
	return date;
}
public String getMonth() {
	
	LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	return localDate.getMonth().toString();
}
public void setMonth(String month) {
	this.month = month;
}
public void setDate(Date date) {
	this.date = date;
}
public Integer getAmount() {
	return amount;
}
public void setAmount(Integer amount) {
	this.amount = amount;
}
@Override
public String toString() {
	return "SuburbDto [date=" + date + ", amount=" + amount + "]";
}



}