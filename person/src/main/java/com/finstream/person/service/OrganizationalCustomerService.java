package com.finstream.person.service;

import org.springframework.stereotype.Service;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;
import com.finstream.person.dto.CustomerDto;
import com.finstream.person.dto.CustomerInputDto;
import com.finstream.person.dto.organizational.OrganizationalCustomerDto;
import com.finstream.person.repository.CustomerRepository;
import com.finstream.person.repository.organizational.OrganizationalCustomerRepository;

@Service
public class OrganizationalCustomerService {
    private final OrganizationalCustomerRepository repository;

    public OrganizationalCustomerService(OrganizationalCustomerRepository repository) {
        this.repository = repository;
    }

    public OrganizationalCustomerDto createCustomer(CustomerInputDto input) {
        // Customer-level fields only
        // implementation will depend on how we map the abstract Customer
        return null;
    }

    public OrganizationalCustomerDto getCustomer(Long customerId) {
        OrganizationalCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        return null;
    }

    public OrganizationalCustomerDto updateCustomer(
            Long customerId,
            CustomerInputDto input
    ) {
        OrganizationalCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        // Update Customer-level fields only

        return null;
    }

    public void deleteCustomer(Long customerId) {
        OrganizationalCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        repository.delete(customer);
    }
}
