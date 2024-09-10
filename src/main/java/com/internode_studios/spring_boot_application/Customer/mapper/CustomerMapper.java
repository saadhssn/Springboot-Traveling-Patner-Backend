package com.internode_studios.spring_boot_application.Customer.mapper;

import com.internode_studios.spring_boot_application.Customer.dto.CustomerDto;
import com.internode_studios.spring_boot_application.Customer.entity.Customer;


public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getEmail_address(),
                customer.getDob(),
                customer.getUsername()
        );
    }

    public static Customer mapToCustomer(CustomerDto customerDto){
        return new Customer(
                customerDto.getId(),
                customerDto.getFirst_name(),
                customerDto.getLast_name(),
                customerDto.getEmail_address(),
                customerDto.getDob(),
                customerDto.getUsername()
        );
    }
}
