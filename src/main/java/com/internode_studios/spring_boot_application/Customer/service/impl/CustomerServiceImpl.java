package com.internode_studios.spring_boot_application.Customer.service.impl;

import com.internode_studios.spring_boot_application.Customer.dto.CustomerDto;
import com.internode_studios.spring_boot_application.Customer.entity.Customer;
import com.internode_studios.spring_boot_application.Customer.exception.ResourceNotFoundException;
import com.internode_studios.spring_boot_application.Customer.mapper.CustomerMapper;
import com.internode_studios.spring_boot_application.Customer.repository.CustomerRepository;
import com.internode_studios.spring_boot_application.Customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("User is not exist with given id : " + customerId));

        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {

        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map((customer) -> CustomerMapper.mapToCustomerDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updateCustomer(Long customerId, CustomerDto updateCustomer) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with given id : " + customerId)
        );

        customer.setFirst_name(updateCustomer.getFirst_name());
        customer.setLast_name(updateCustomer.getLast_name());
        customer.setDob(updateCustomer.getDob());

        Customer updateCustomerObj = customerRepository.save(customer);

        return CustomerMapper.mapToCustomerDto(updateCustomerObj);
    }

    @Override
    public void deleteCustomer(Long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("User is not exists with given id : " + customerId)
        );

        customerRepository.deleteById(customerId);
    }

}

