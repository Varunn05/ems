package com.example.ems.mapper;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.model.Employees;

public class EmployeeMapper {
    
    public static EmployeeDto mapToEmployeeDto(Employees employees) {
        return new EmployeeDto(
                employees.getId(),
                employees.getFirstname(),
                employees.getLastname(),
                employees.getEmail()
        );
    }

    public static Employees mapToEmployee(EmployeeDto employeeDto) {
        return new Employees(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
