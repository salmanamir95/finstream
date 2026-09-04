package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.NonProfitCustomer;
import com.finstream.person.dto.organizational.NonProfitCustomerDto;
import com.finstream.person.mapper.NonProfitCustomerMapper;
import com.finstream.person.service.organizational.NonProfitCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/non-profit-customers")
public class NonProfitCustomerController extends
        OrganizationalCustomerController<NonProfitCustomer, NonProfitCustomerDto> {

    private final NonProfitCustomerService service;
    private final NonProfitCustomerMapper mapper;

    public NonProfitCustomerController(
            NonProfitCustomerService service,
            NonProfitCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected NonProfitCustomerDto toDto(Customer customer) {
        return mapper.toDto((NonProfitCustomer) customer);
    }

    @Override
    protected NonProfitCustomer toEntity(NonProfitCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected NonProfitCustomer createEntity(NonProfitCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<NonProfitCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected NonProfitCustomer updateEntity(NonProfitCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}