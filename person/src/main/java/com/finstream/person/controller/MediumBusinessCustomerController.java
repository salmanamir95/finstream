package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.MediumBusinessCustomer;
import com.finstream.person.dto.organizational.business.MediumBusinessCustomerDto;
import com.finstream.person.mapper.MediumBusinessCustomerMapper;
import com.finstream.person.service.organizational.business.MediumBusinessCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/medium-business-customers")
public class MediumBusinessCustomerController extends
        BusinessCustomerController<MediumBusinessCustomer, MediumBusinessCustomerDto> {

    private final MediumBusinessCustomerService service;
    private final MediumBusinessCustomerMapper mapper;

    public MediumBusinessCustomerController(
            MediumBusinessCustomerService service,
            MediumBusinessCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected MediumBusinessCustomerDto toDto(Customer customer) {
        return mapper.toDto((MediumBusinessCustomer) customer);
    }

    @Override
    protected MediumBusinessCustomer toEntity(MediumBusinessCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected MediumBusinessCustomer createEntity(MediumBusinessCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<MediumBusinessCustomer> findEntity(Long customerId) {
        return Optional.ofNullable(service.findByIdOrNull(customerId));
    }

    @Override
    protected MediumBusinessCustomer updateEntity(MediumBusinessCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}