package com.example.demoproject.controller.impl;

import com.example.demoproject.controller.GenericController;
import com.example.demoproject.domain.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class GenericControllerImpl<Model extends BaseEntity> implements GenericController<Model> {
    private final JpaRepository<Model, UUID> repository;

    @Override
    @GetMapping
    public ResponseEntity<List<Model>> getAll() {
        List<Model> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @Override
    @PostMapping
    public ResponseEntity<Model> create(@RequestBody @Validated Model json) {
        Model saved = repository.save(json);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    @GetMapping("{id}")
    public ResponseEntity<Model> getById(UUID id) {
        Optional<Model> finded = repository.findById(id);
        return finded.isEmpty()?
                ResponseEntity.notFound().build():
                ResponseEntity.ok(finded.get());
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<Model> update(UUID id, Model json) {
        if (repository.existsById(id)){
            json.setId(id);
            Model updated = repository.save(json);
            return ResponseEntity.ok().body(updated);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(UUID id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.ok(HttpStatus.OK);
        }
        else
            return ResponseEntity.notFound().build();

    }
}
