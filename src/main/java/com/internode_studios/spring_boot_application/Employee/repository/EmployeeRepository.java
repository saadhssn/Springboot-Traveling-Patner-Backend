package com.internode_studios.spring_boot_application.Employee.repository;

import com.internode_studios.spring_boot_application.Employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
