package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.InstitutionalCustomer;
import com.finstream.person.dto.organizational.InstitutionalCustomerDto;
import com.finstream.person.mapper.InstitutionalCustomerMapper;
import com.finstream.person.service.organizational.InstitutionalCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/institutional-customers")
public class InstitutionalCustomerController extends
        OrganizationalCustomerController<InstitutionalCustomer, InstitutionalCustomerDto> {

    private final InstitutionalCustomerService service;
    private final InstitutionalCustomerMapper mapper;

    public InstitutionalCustomerController(
            InstitutionalCustomerService service,
            InstitutionalCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected InstitutionalCustomerDto toDto(Customer customer) {
        return mapper.toDto((InstitutionalCustomer) customer);
    }

    @Override
    protected InstitutionalCustomer toEntity(InstitutionalCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected InstitutionalCustomer createEntity(InstitutionalCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<InstitutionalCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected InstitutionalCustomer updateEntity(InstitutionalCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}