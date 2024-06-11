package com.awards.customer.mapper;

import org.mapstruct.Mapper;

import com.awards.customer.dto.CustomerDto;
import com.awards.customer.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
  Customer toEntity(CustomerDto customerDto);
  CustomerDto toDto(Customer suburb);
}


