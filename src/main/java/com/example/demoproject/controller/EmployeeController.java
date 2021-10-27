package com.example.demoproject.controller;

import com.example.demoproject.domain.Employee;
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
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Validated Employee employee, UriComponentsBuilder uriComponentsBuilder) {
        Employee saved = service.save(employee);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        Employee employee = service.getById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody @Validated Employee employee, UriComponentsBuilder uriComponentsBuilder) {
        Employee updated = service.update(id, employee);
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
