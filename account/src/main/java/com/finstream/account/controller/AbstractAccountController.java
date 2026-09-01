package com.finstream.account.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.service.AccountServiceContract;
import com.finstream.common.response.GenericResponse;

public abstract class AbstractAccountController<
        D extends AccountDTO,
        I extends AccountInput>
        implements AccountControllerContract<D, I> {

    protected final AccountServiceContract<?, I, D> service;

    protected AbstractAccountController(
            AccountServiceContract<?, I, D> service) {

        this.service = service;
    }

    @Override
    @PostMapping
    public GenericResponse<D> create(
            @RequestBody I input) {

        return GenericResponse.success(
                service.create(input),
                "Account created successfully");
    }

    @Override
    @GetMapping("/{id}")
    public GenericResponse<D> getById(
            @PathVariable Long id) {

        return GenericResponse.success(
                service.getById(id),
                "Account retrieved successfully");
    }

    @Override
    @GetMapping
    public GenericResponse<List<D>> getAll() {

        return GenericResponse.success(
                service.getAll(),
                "Accounts retrieved successfully");
    }

    @Override
    @PutMapping("/{id}")
    public GenericResponse<D> update(
            @PathVariable Long id,
            @RequestBody I input) {

        return GenericResponse.success(
                service.update(id, input),
                "Account updated successfully");
    }

    @Override
    @DeleteMapping("/{id}")
    public GenericResponse<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return GenericResponse.success(
                null,
                "Account deleted successfully");
    }
}