package com.example.demoproject.service;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.dto.EmployeeDto;
import com.example.demoproject.mapper.EmployeeMapper;
import com.example.demoproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    public List<EmployeeDto> findAll() {
        log.info("findAll() - start");
        List<EmployeeDto> list = repository.findAll()
                .stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
        log.info("findAll(), list = {}", list);
        return list;
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        log.info("save() - start, dto = {}", dto);
        Employee saved = repository.save(mapper.dtoToModel(dto));
        log.info("save(), saved = {}", saved);
        return mapper.modelToDto(saved);
    }

    @Override
    public EmployeeDto getById(UUID id) {
        log.info("getById() - start, id = {}", id);
        Employee finded = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        log.info("getById(), finded = {}", finded);
        return mapper.modelToDto(finded);
    }

    @Override
    public void delete(UUID id) {
        log.info("delete() - start, id = {}", id);
        Employee finded = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        log.info("delete(), finded = {}", finded);
        repository.deleteById(id);
    }

    @Override
    public EmployeeDto update(UUID id, EmployeeDto dto) {
        log.info("update(), id = {}, dto = {}", id, dto);
        Employee finded = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        log.info("update(), finded = {}", finded);
        dto.setId(id);
        Employee saved = repository.save(mapper.dtoToModel(dto));
        log.info("update(), saved = {}", saved);
        return mapper.modelToDto(saved);
    }
}
