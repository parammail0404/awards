package com.awards.customer.mapper;

import com.awards.customer.dto.CustomerDto;
import com.awards.customer.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T22:29:45+0530",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 1.4.100.v20220318-0906, environment: Java 17.0.2 (Eclipse Adoptium)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toEntity(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setAmount( customerDto.getAmount() );
        customer.setDate( customerDto.getDate() );
        customer.setMonth( customerDto.getMonth() );

        return customer;
    }

    @Override
    public CustomerDto toDto(Customer suburb) {
        if ( suburb == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setAmount( suburb.getAmount() );
        customerDto.setDate( suburb.getDate() );
        customerDto.setMonth( suburb.getMonth() );

        return customerDto;
    }
}
