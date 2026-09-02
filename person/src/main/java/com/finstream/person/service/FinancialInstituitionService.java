package com.finstream.person.service;

import org.springframework.stereotype.Service;

import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.domain.customer.organizationalCustomer.FinancialInstitutionCustomer;
import com.finstream.person.dto.CustomerInputDto;
import com.finstream.person.dto.IndividualCustomerDto;
import com.finstream.person.dto.organizational.FinancialInstitutionCustomerDto;
import com.finstream.person.repository.IndividualCustomerRepository;

@Service
public class FinancialInstituitionService {
    private final FinancialInstituitionService repository;
    

    public FinancialInstituitionService(FinancialInstituitionService repository) {
        this.repository = repository;
    }

    public FinancialInstitutionCustomerDto createCustomer(CustomerInputDto input) {
        // Customer-level fields only
        // implementation will depend on how we map the abstract Customer
        return null;
    }

    public FinancialInstitutionCustomerDto getCustomer(Long customerId) {
        FinancialInstitutionCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        return null;
    }

    public FinancialInstitutionCustomerDto updateCustomer(
            Long customerId,
            CustomerInputDto input
    ) {
        FinancialInstitutionCustomerDto customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        // Update Customer-level fields only

        return null;
    }

    public void deleteCustomer(Long customerId) {
        FinancialInstitutionCustomerDto customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        repository.delete(customer);
    }
}
