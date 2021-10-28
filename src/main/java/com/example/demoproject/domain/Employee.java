package com.example.demoproject.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Min(0)
    @Max(120)
    @NotNull
    private Integer age;

    private Double experience;

    @NotNull
    @Column(name = "job_id")
    private Position position;

    @NotNull
    @Column(name = "project_id")
    private Project project;

    @NotNull
    @Column(name = "hire_date")
    private LocalDate hireDate;

    @NotNull
    @Column(name = "gender_id")
    private Gender gender;
}
