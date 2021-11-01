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
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private final UUID ID1 = UUID.randomUUID();
    private final UUID ID2 = UUID.randomUUID();

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
                .id(ID1)
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
                .id(ID1)
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
                .id(ID2)
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
                .id(ID2)
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
        when(repository.findById(ID1))
                .thenReturn(Optional.of(employee1));
        when(mapper.modelToDto(employee1)).thenReturn(dto1);

        EmployeeDto findedById = service.getById(ID1);

        assertThat(findedById.getId())
                .isNotNull()
                .isEqualTo(ID1);
    }

    @Test
    void deleteCallRepository() {
        when(repository.findById(ID1))
                .thenReturn(Optional.of(employee1));
        service.delete(ID1);
        verify(repository, times(1)).deleteById(ID1);
    }

    @Test
    void updateMethodReturnSameDto() {
        when(repository.findById(ID1))
                .thenReturn(Optional.of(employee1));
        when(repository.save(employee1)).thenReturn(employee1);
        when(mapper.modelToDto(employee1)).thenReturn(dto1);
        when(mapper.dtoToModel(dto1)).thenReturn(employee1);

        EmployeeDto updated = service.update(ID1, dto1);
        assertThat(updated)
                .isNotNull()
                .isEqualTo(dto1);

    }

    @Test()
    void findByIdShouldThrowResourceNFEWhenIdIsNotFoundTest() {
        assertThrows(ResourceNotFoundException.class, () -> service.getById(ID1));
    }

    @Test()
    void deleteShouldThrowResourceNFEWhenIdIsNotFoundTest() {
        var randomID = UUID.randomUUID();
        assertThrows(ResourceNotFoundException.class, () -> service.delete(ID1));
    }

    @Test()
    void updateShouldThrowResourceNFEWhenIdIsNotFoundTest() {
        var randomID = UUID.randomUUID();
        assertThrows(ResourceNotFoundException.class, () -> service.update(ID1, dto1));
    }
}
