package com.internode_studios.spring_boot_application.Employee.service.impl;

import com.internode_studios.spring_boot_application.Employee.dto.EmployeeDto;
import com.internode_studios.spring_boot_application.Employee.entity.Employee;
import com.internode_studios.spring_boot_application.Employee.mapper.EmployeeMapper;
import com.internode_studios.spring_boot_application.Employee.repository.EmployeeRepository;
import com.internode_studios.spring_boot_application.Employee.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }
}
