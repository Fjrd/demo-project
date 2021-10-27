package com.example.demoproject.service;

import com.example.demoproject.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto save(EmployeeDto dto);

    EmployeeDto getById(UUID id);

    void delete(UUID id);

    EmployeeDto update(UUID id, EmployeeDto dto);
}
