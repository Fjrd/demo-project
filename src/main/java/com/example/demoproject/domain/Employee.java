package com.example.demoproject.domain;

import lombok.*;
import lombok.EqualsAndHashCode.Include;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@ToString
@Validated
@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Include
    UUID id;

    String firstName;
    String lastName;
    Integer age;
    Double experience;

    @Column(name = "job_id")
    Position position;

    @Column(name = "project_id")
    Project project;

    @Column(name = "hire_date")
    LocalDate hireDate;

    @Column(name = "gender_id")
    Gender gender;
}
