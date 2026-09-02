package com.finstream.person.service;


import org.springframework.stereotype.Service;

import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.dto.CustomerInputDto;
import com.finstream.person.dto.IndividualCustomerDto;
import com.finstream.person.repository.IndividualCustomerRepository;

@Service
public class IndividualCustomerService {   

    private final IndividualCustomerRepository repository;
    

    public IndividualCustomerService(IndividualCustomerRepository repository) {
        this.repository = repository;
    }

    public IndividualCustomerDto createCustomer(CustomerInputDto input) {
        // Customer-level fields only
        // implementation will depend on how we map the abstract Customer
        return null;
    }

    public IndividualCustomerDto getCustomer(Long customerId) {
        IndividualCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        return null;
    }

    public IndividualCustomerDto updateCustomer(
            Long customerId,
            CustomerInputDto input
    ) {
        IndividualCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        // Update Customer-level fields only

        return null;
    }

    public void deleteCustomer(Long customerId) {
        IndividualCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        repository.delete(customer);
    }
}