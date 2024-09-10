package com.internode_studios.spring_boot_application.Customer.repository;

import com.internode_studios.spring_boot_application.Customer.entity.Customer;
import com.internode_studios.spring_boot_application.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
