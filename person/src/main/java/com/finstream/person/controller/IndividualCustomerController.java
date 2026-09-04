package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.dto.IndividualCustomerDto;
import com.finstream.person.mapper.IndividualCustomerMapper;
import com.finstream.person.service.IndividualCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/individual-customers")
public class IndividualCustomerController
        extends AbstractCustomerController<IndividualCustomer, IndividualCustomerDto> {

    private final IndividualCustomerService service;
    private final IndividualCustomerMapper mapper;

    public IndividualCustomerController(
            IndividualCustomerService service,
            IndividualCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected IndividualCustomerDto toDto(Customer customer) {
        return mapper.toDto((IndividualCustomer) customer);
    }

    @Override
    protected IndividualCustomer toEntity(IndividualCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected IndividualCustomer createEntity(IndividualCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<IndividualCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected IndividualCustomer updateEntity(IndividualCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}