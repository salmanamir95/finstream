package com.finstream.person.controller;

import com.finstream.common.response.GenericResponse;
import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.BusinessCustomer;
import com.finstream.person.dto.organizational.business.BusinessCustomerDto;
import com.finstream.person.service.organizational.business.BusinessCustomerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public abstract class BusinessCustomerController<
        E extends BusinessCustomer,
        D extends BusinessCustomerDto>
        extends OrganizationalCustomerController<E, D> {

    protected final BusinessCustomerService businessCustomerService;

    protected BusinessCustomerController(
            BusinessCustomerService businessCustomerService) {
        super(businessCustomerService);
        this.businessCustomerService = businessCustomerService;
    }

    @GetMapping("/search/tax-identification-number")
    public GenericResponse<D> findByTaxIdentificationNumber(
            @RequestParam String taxIdentificationNumber) {
        Optional<BusinessCustomer> customer =
                businessCustomerService.findByTaxIdentificationNumber(taxIdentificationNumber);
        return customer
                .map(value -> GenericResponse.success(
                        toDto(value), "Customer retrieved successfully"))
                .orElseGet(() -> GenericResponse.failure("Customer not found"));
    }
}