package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.LargeBusinessCustomer;
import com.finstream.person.dto.organizational.business.LargeBusinessCustomerDto;
import com.finstream.person.mapper.LargeBusinessCustomerMapper;
import com.finstream.person.service.organizational.business.LargeBusinessCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/large-business-customers")
public class LargeBusinessCustomerController extends
        BusinessCustomerController<LargeBusinessCustomer, LargeBusinessCustomerDto> {

    private final LargeBusinessCustomerService service;
    private final LargeBusinessCustomerMapper mapper;

    public LargeBusinessCustomerController(
            LargeBusinessCustomerService service,
            LargeBusinessCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected LargeBusinessCustomerDto toDto(Customer customer) {
        return mapper.toDto((LargeBusinessCustomer) customer);
    }

    @Override
    protected LargeBusinessCustomer toEntity(LargeBusinessCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected LargeBusinessCustomer createEntity(LargeBusinessCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<LargeBusinessCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected LargeBusinessCustomer updateEntity(LargeBusinessCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}