package com.example.demoproject.repository;

import com.example.demoproject.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> getByFirstNameAndLastName(String firstName, String lastName);

}
