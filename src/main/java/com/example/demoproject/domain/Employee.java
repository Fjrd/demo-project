package com.example.demoproject.domain;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter(AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@ToString(doNotUseGetters = true)
@Validated
@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true, callSuper = true)
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;

    @Min(0)
    @Max(120)
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
