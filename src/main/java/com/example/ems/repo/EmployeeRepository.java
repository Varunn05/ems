package com.example.ems.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ems.model.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long>{

}
