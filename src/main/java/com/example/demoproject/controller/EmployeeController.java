package com.example.demoproject.controller;

import com.example.demoproject.dto.EmployeeDto;
import com.example.demoproject.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAll() {
        log.info("getAll() - start");
        List<EmployeeDto> list = service.findAll();
        log.info("getAll(), list = {}", list);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> create(@RequestBody @Validated EmployeeDto dto, UriComponentsBuilder uriComponentsBuilder) {
        log.info("create() - start, dto = {}, uriComponentsBuilder = {}", dto, uriComponentsBuilder);
        EmployeeDto saved = service.save(dto);
        log.info("create(), saved = {}", saved);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        log.info("create(), uri = {}", uri);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id) {
        log.info("getById() - start, id = {}", id);
        EmployeeDto dto = service.getById(id);
        log.info("getById(), dto = {}", dto);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable UUID id, @RequestBody @Validated EmployeeDto dto, UriComponentsBuilder uriComponentsBuilder) {
        log.info("update() - start, id = {}, dto = {}, uriComponentsBuilder = {}", id, dto, uriComponentsBuilder);
        EmployeeDto updated = service.update(id, dto);
        log.info("update(), updated = {}", updated);
        URI uri = uriComponentsBuilder
                .path("/api/employees/{id}")
                .buildAndExpand(updated.getId())
                .toUri();
        log.info("update(), uri = {}", uri);
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable UUID id) {
        log.info("deleteById() - start, id = {}", id);
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
