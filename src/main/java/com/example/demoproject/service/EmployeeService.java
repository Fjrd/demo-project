package com.example.demoproject.service;

import com.example.demoproject.domain.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<Employee> findAll();

    Employee save(Employee employee);

    Employee getById(UUID id);

    void delete(UUID id);

    Employee update(UUID id, Employee employee);
}
