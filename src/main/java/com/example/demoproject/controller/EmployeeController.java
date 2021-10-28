package com.example.demoproject.controller;

import com.example.demoproject.dto.EmployeeDto;
import com.example.demoproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody @Validated EmployeeDto dto, UriComponentsBuilder uriComponentsBuilder) {
        EmployeeDto saved = service.save(dto);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id) {
        EmployeeDto dto = service.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable UUID id, @RequestBody @Validated EmployeeDto dto, UriComponentsBuilder uriComponentsBuilder) {
        EmployeeDto updated = service.update(id, dto);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
