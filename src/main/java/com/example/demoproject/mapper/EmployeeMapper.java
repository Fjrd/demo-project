package com.example.demoproject.mapper;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.dto.EmployeeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto modelToDto(Employee employee);
    Employee dtoToModel (EmployeeDto employeeDto);
}
