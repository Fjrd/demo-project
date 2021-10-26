package com.example.demoproject.controller;

import com.example.demoproject.domain.Employee;
import com.example.demoproject.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
    private final EmployeeRepository repository;

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        List<Employee> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Validated Employee json, UriComponentsBuilder uriComponentsBuilder) {
        Employee saved = repository.save(json);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable UUID id) {
        Employee finded = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        return ResponseEntity.ok(finded);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable UUID id, @RequestBody @Validated Employee json, UriComponentsBuilder uriComponentsBuilder) {
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        json.setId(id);
        Employee updated = repository.save(json);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable UUID id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
        repository.delete(employee);
        return ResponseEntity.ok().build();
    }
}
