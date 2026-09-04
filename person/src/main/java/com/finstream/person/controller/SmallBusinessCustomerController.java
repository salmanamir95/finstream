package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.SmallBusinessCustomer;
import com.finstream.person.dto.organizational.business.SmallBusinessCustomerDto;
import com.finstream.person.mapper.SmallBusinessCustomerMapper;
import com.finstream.person.service.organizational.business.SmallBusinessCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/small-business-customers")
public class SmallBusinessCustomerController extends
        BusinessCustomerController<SmallBusinessCustomer, SmallBusinessCustomerDto> {

    private final SmallBusinessCustomerService service;
    private final SmallBusinessCustomerMapper mapper;

    public SmallBusinessCustomerController(
            SmallBusinessCustomerService service,
            SmallBusinessCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected SmallBusinessCustomerDto toDto(Customer customer) {
        return mapper.toDto((SmallBusinessCustomer) customer);
    }

    @Override
    protected SmallBusinessCustomer toEntity(SmallBusinessCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected SmallBusinessCustomer createEntity(SmallBusinessCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<SmallBusinessCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected SmallBusinessCustomer updateEntity(SmallBusinessCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}