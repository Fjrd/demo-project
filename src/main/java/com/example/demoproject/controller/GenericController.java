package com.example.demoproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface GenericController<Model> {
    ResponseEntity<List<Model>> getAll();
    ResponseEntity<Model> create(@RequestBody @Valid Model json);
    ResponseEntity<Model> getById(@PathVariable UUID id);
    ResponseEntity<Model> update(@PathVariable UUID id, @RequestBody @Valid Model json);
    ResponseEntity<HttpStatus> deleteById(@PathVariable UUID id);
}
