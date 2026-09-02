package com.finstream.person.service;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.dto.CustomerDto;
import com.finstream.person.dto.CustomerInputDto;
import com.finstream.person.repository.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository<Customer> repository;
    

    public CustomerService(CustomerRepository<Customer> repository) {
        this.repository = repository;
    }

    public CustomerDto createCustomer(CustomerInputDto input) {
        // Customer-level fields only
        // implementation will depend on how we map the abstract Customer
        return null;
    }

    public CustomerDto getCustomer(Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        return null;
    }

    public CustomerDto updateCustomer(
            Long customerId,
            CustomerInputDto input
    ) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        // Update Customer-level fields only

        return null;
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        repository.delete(customer);
    }
}