package com.internode_studios.spring_boot_application.Customer.service;

import com.internode_studios.spring_boot_application.Customer.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long customerId);

    List<CustomerDto> getAllCustomer();

    CustomerDto updateCustomer(Long customerId, CustomerDto updateCustomer);

    void deleteCustomer(Long employeeId);
}
