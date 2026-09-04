package com.finstream.person.controller;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.FinancialInstitutionCustomer;
import com.finstream.person.dto.organizational.FinancialInstitutionCustomerDto;
import com.finstream.person.mapper.FinancialInstitutionCustomerMapper;
import com.finstream.person.service.organizational.FinancialInstitutionCustomerService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/person/financial-institution-customers")
public class FinancialInstitutionCustomerController extends
        OrganizationalCustomerController<FinancialInstitutionCustomer, FinancialInstitutionCustomerDto> {

    private final FinancialInstitutionCustomerService service;
    private final FinancialInstitutionCustomerMapper mapper;

    public FinancialInstitutionCustomerController(
            FinancialInstitutionCustomerService service,
            FinancialInstitutionCustomerMapper mapper) {
        super(service);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected FinancialInstitutionCustomerDto toDto(Customer customer) {
        return mapper.toDto((FinancialInstitutionCustomer) customer);
    }

    @Override
    protected FinancialInstitutionCustomer toEntity(FinancialInstitutionCustomerDto dto) {
        return mapper.toEntity(dto);
    }

    @Override
    protected FinancialInstitutionCustomer createEntity(FinancialInstitutionCustomer entity) {
        return service.createCustomer(entity);
    }

    @Override
    protected Optional<FinancialInstitutionCustomer> findEntity(Long customerId) {
        return service.findById(customerId);
    }

    @Override
    protected FinancialInstitutionCustomer updateEntity(FinancialInstitutionCustomer entity) {
        return service.updateCustomer(entity);
    }

    @Override
    protected void deleteEntity(Long customerId) {
        service.deleteCustomer(customerId);
    }
}