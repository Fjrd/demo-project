package com.example.demoproject.service;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee getById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.deleteById(id);
    }

    @Override
    public Employee update(UUID id, Employee employee) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        employee.setId(id);
        return repository.save(employee);
    }
}
