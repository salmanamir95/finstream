package com.finstream.person.controller;

import com.finstream.common.response.GenericResponse;
import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;
import com.finstream.person.dto.organizational.OrganizationalCustomerDto;
import com.finstream.person.service.organizational.OrganizationalCustomerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public abstract class OrganizationalCustomerController<
        E extends OrganizationalCustomer,
        D extends OrganizationalCustomerDto>
        extends AbstractCustomerController<E, D> {

    protected final OrganizationalCustomerService organizationalCustomerService;

    protected OrganizationalCustomerController(
            OrganizationalCustomerService organizationalCustomerService) {
        super(organizationalCustomerService);
        this.organizationalCustomerService = organizationalCustomerService;
    }

    @GetMapping("/search/registration-number")
    public GenericResponse<D> findByRegistrationNumber(
            @RequestParam String registrationNumber) {
        Optional<OrganizationalCustomer> customer =
                organizationalCustomerService.findByRegistrationNumber(registrationNumber);
        return customer
                .map(value -> GenericResponse.success(
                        toDto(value), "Customer retrieved successfully"))
                .orElseGet(() -> GenericResponse.failure("Customer not found"));
    }
}