package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.GovernmentCustomer;
import com.finstream.person.dto.organizational.GovernmentCustomerDto;
import com.finstream.person.mapper.GovernmentCustomerMapper;
import com.finstream.person.service.organizational.GovernmentCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/government-customers")
public class GovernmentCustomerController extends
        OrganizationalCustomerController<GovernmentCustomer, GovernmentCustomerDto> {

    private final GovernmentCustomerService service;
    private final GovernmentCustomerMapper mapper;

    public GovernmentCustomerController(
            GovernmentCustomerService service,
            GovernmentCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected GovernmentCustomerDto toDto(Customer customer) {
        return mapper.toDto((GovernmentCustomer) customer);
    }

    @Override
    protected GovernmentCustomer toEntity(GovernmentCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected GovernmentCustomer createEntity(GovernmentCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<GovernmentCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected GovernmentCustomer updateEntity(GovernmentCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}