package com.example.demoproject.repository;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.domain.Gender;
import com.example.demoproject.domain.Position;
import com.example.demoproject.domain.Project;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    private final EmployeeRepository repository = null;
    private Employee employee1;
    private static final String firstName = "PETYA";
    private static final String lastName = "PETROV";

    @BeforeEach
    void setup(){
        employee1 = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .age(30)
                .experience(2.5)
                .position(Position.BASIC_EMPLOYEE)
                .project(Project.PROJECT_1)
                .hireDate(LocalDate.now().minusMonths(30))
                .gender(Gender.MALE)
                .build();
        repository.save(employee1);
    }

    @AfterEach
    void cleanup(){
        repository.delete(employee1);
    }

    @Test
    public void getByFirstNameAndLastNameWorksCorrectly(){
        List<Employee> byFirstNameAndLastName = repository.getByFirstNameAndLastName(firstName, lastName);
        assertThat(byFirstNameAndLastName).contains(employee1);
    }
}