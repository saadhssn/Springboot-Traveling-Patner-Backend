package com.internode_studios.spring_boot_application.Employee.service;

import com.internode_studios.spring_boot_application.Employee.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);
}
