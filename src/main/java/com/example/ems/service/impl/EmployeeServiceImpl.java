package com.example.ems.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ems.dto.EmployeeDto;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.mapper.EmployeeMapper;
import com.example.ems.model.Employees;
import com.example.ems.repo.EmployeeRepository;
import com.example.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employees employees = EmployeeMapper.mapToEmployee(employeeDto);
        Employees savedEmployee = employeeRepository.save(employees);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeesById(Long employeeId) {
        Employees employees = employeeRepository.findById(employeeId)
            .orElseThrow(() -> 
                    new ResourceNotFoundException("Employee id not exists with the given Id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employees);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employees> employees = employeeRepository.findAll();
        return employees.stream()
            .map(EmployeeMapper::mapToEmployeeDto)
            .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employees employees = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exists with the givne id: " +employeeId)   
        );

        employees.setFirstname(updateEmployee.getFirstName());
        employees.setLastname(updateEmployee.getLastName());
        employees.setEmail(updateEmployee.getEmail());

        Employees updateEmployeeObj = employeeRepository.save(employees);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employees employees = employeeRepository.findById(employeeId).orElseThrow(
                () -> new ResourceNotFoundException("Employee does not exists with the givne id: " +employeeId)   
        );

        employeeRepository.deleteById(employeeId);
    }    
}
 