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
    private UUID id;

    private String firstName;
    private String lastName;
    private Integer age;
    private Double experience;

    @Column(name = "job_id")
    private Position position;

    @Column(name = "project_id")
    private Project project;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "gender_id")
    private Gender gender;
}
