package com.finstream.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.service.AccountServiceContract;

public abstract class AbstractAccountController<D extends AccountDTO, I extends AccountInput> {

    protected final AccountServiceContract<?, I, D> service;

    protected AbstractAccountController(
            AccountServiceContract<?, I, D> service) {

        this.service = service;
    }

    @PostMapping
    public ResponseEntity<D> create(
            @RequestBody I input) {

        return ResponseEntity.ok(
                service.create(input));
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<D>> getAll() {

        return ResponseEntity.ok(
                service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(
            @PathVariable Long id,
            @RequestBody I input) {

        return ResponseEntity.ok(
                service.update(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
