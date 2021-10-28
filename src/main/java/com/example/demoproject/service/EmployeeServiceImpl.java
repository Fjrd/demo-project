package com.example.demoproject.service;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.dto.EmployeeDto;
import com.example.demoproject.mapper.EmployeeMapper;
import com.example.demoproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeDto> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        Employee saved = repository.save(mapper.dtoToModel(dto));
        return mapper.modelToDto(saved);
    }

    @Override
    public EmployeeDto getById(UUID id) {
        Employee finded = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        return mapper.modelToDto(finded);
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.deleteById(id);
    }

    @Override
    public EmployeeDto update(UUID id, EmployeeDto dto) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        dto.setId(id);
        Employee saved = repository.save(mapper.dtoToModel(dto));
        return mapper.modelToDto(saved);
    }
}
