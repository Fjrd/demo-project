package com.example.demoproject.dto;

import com.example.demoproject.domain.Gender;
import com.example.demoproject.domain.Position;
import com.example.demoproject.domain.Project;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class EmployeeDto {

    @NotNull
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
    private Position position;

    @NotNull
    private Project project;

    @NotNull
    private LocalDate hireDate;

    @NotNull
    private Gender gender;
}
