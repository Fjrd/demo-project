package com.example.demoproject.controller.impl;

import com.example.demoproject.controller.EmployeeController;
import com.example.demoproject.domain.Employee;
import com.example.demoproject.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employees")
public class EmployeeControllerImpl extends GenericControllerImpl<Employee> implements EmployeeController {
    public EmployeeControllerImpl(EmployeeRepository repository) {
        super(repository);
    }
}
