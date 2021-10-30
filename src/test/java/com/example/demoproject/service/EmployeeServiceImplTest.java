package com.example.demoproject.service;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.domain.Gender;
import com.example.demoproject.domain.Position;
import com.example.demoproject.domain.Project;
import com.example.demoproject.dto.EmployeeDto;
import com.example.demoproject.mapper.EmployeeMapper;
import com.example.demoproject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @Mock
    private EmployeeMapper mapper;

    @InjectMocks
    private EmployeeServiceImpl service;

    private Employee employee1;
    private Employee employee2;
    private EmployeeDto dto1;
    private EmployeeDto dto2;

    @BeforeEach
    void setUp() {
        service = new EmployeeServiceImpl(repository, mapper);
        employee1 = Employee.builder()
                .id(UUID.randomUUID())
                .firstName("fn1")
                .lastName("ln2")
                .age(33)
                .experience(5.0)
                .position(Position.BASIC_EMPLOYEE)
                .project(Project.PROJECT_1)
                .hireDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        dto1 = EmployeeDto.builder()
                .id(employee1.getId())
                .firstName("fn1")
                .lastName("ln2")
                .age(33)
                .experience(5.0)
                .position(Position.BASIC_EMPLOYEE)
                .project(Project.PROJECT_1)
                .hireDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        employee2 = Employee.builder()
                .id(UUID.randomUUID())
                .firstName("fn2")
                .lastName("ln2")
                .age(44)
                .experience(10.0)
                .position(Position.BASIC_EMPLOYEE)
                .project(Project.PROJECT_2)
                .hireDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();

        dto2 = EmployeeDto.builder()
                .id(employee2.getId())
                .firstName("fn2")
                .lastName("ln2")
                .age(44)
                .experience(10.0)
                .position(Position.BASIC_EMPLOYEE)
                .project(Project.PROJECT_2)
                .hireDate(LocalDate.now())
                .gender(Gender.MALE)
                .build();
    }

    @Test
    void findAllWorksCorrectlyTest() {
        when(repository.findAll()).thenReturn(List.of(employee1, employee2));
        when(mapper.modelToDto(employee1)).thenReturn(dto1);
        when(mapper.modelToDto(employee2)).thenReturn(dto2);

        List<EmployeeDto> finded = service.findAll();

        assertThat(finded)
                .isNotNull()
                .isEqualTo(List.of(dto1, dto2));
    }

    @Test
    void saveReturnDtoTest() {
        when(repository.save(employee1)).thenReturn(employee1);
        when(mapper.modelToDto(employee1)).thenReturn(dto1);
        when(mapper.dtoToModel(dto1)).thenReturn(employee1);

        EmployeeDto saved = service.save(dto1);

        assertThat(saved)
                .isNotNull()
                .isEqualTo(dto1);
    }

    @Test
    void getByIdReturnSameIdDtoTest() {
        when(repository.findById(employee1.getId()))
                .thenReturn(Optional.of(employee1));
        when(mapper.modelToDto(employee1)).thenReturn(dto1);

        EmployeeDto findedById = service.getById(employee1.getId());

        assertThat(findedById.getId())
                .isNotNull()
                .isEqualTo(employee1.getId());
    }

    @Test
    void deleteCallRepository() {
        when(repository.findById(employee1.getId()))
                .thenReturn(Optional.of(employee1));
        service.delete(employee1.getId());
        //assert repository delete call at least once
    }

    @Test
    void updateMethodReturnSameDto() {
        when(repository.findById(employee1.getId()))
                .thenReturn(Optional.of(employee1));
        when(repository.save(employee1)).thenReturn(employee1);
        when(mapper.modelToDto(employee1)).thenReturn(dto1);
        when(mapper.dtoToModel(dto1)).thenReturn(employee1);

        EmployeeDto updated = service.update(employee1.getId(), dto1);
        assertThat(updated)
                .isNotNull()
                .isEqualTo(dto1);

    }
}