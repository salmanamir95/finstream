package com.finstream.person.controller;

import com.finstream.common.response.GenericResponse;
import com.finstream.person.domain.customer.Customer;
import com.finstream.person.dto.CustomerDto;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public abstract class AbstractCustomerController<
        E extends Customer,
        D extends CustomerDto>
        extends CustomerController<D> {

    protected AbstractCustomerController(
            com.finstream.person.service.CustomerService customerService) {
        super(customerService);
    }

    protected abstract E toEntity(D dto);

    protected abstract E createEntity(E entity);

    protected abstract Optional<E> findEntity(Long customerId);

    protected abstract E updateEntity(E entity);

    protected abstract void deleteEntity(Long customerId);

    @PostMapping
    public GenericResponse<D> create(@Valid @RequestBody D dto) {
        return GenericResponse.success(
                toDto(createEntity(toEntity(dto))),
                "Customer created successfully");
    }

    @GetMapping("/{customerId}")
    public GenericResponse<D> findById(@PathVariable Long customerId) {
        return GenericResponse.success(
                toDto(findEntity(customerId).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Customer not found"))),
                "Customer retrieved successfully");
    }

    @PutMapping("/{customerId}")
    public GenericResponse<D> update(
            @PathVariable Long customerId,
            @Valid @RequestBody D dto) {
        E entity = toEntity(dto);
        entity.setCustomerId(customerId);
        return GenericResponse.success(
                toDto(updateEntity(entity)),
                "Customer updated successfully");
    }

    @DeleteMapping("/{customerId}")
    public GenericResponse<Void> delete(@PathVariable Long customerId) {
        deleteEntity(customerId);
        return GenericResponse.success(null, "Customer deleted successfully");
    }
}