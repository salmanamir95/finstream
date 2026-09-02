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

    public CustomerDto getCustomer(Long customerId) {
        Customer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId
                        )
                );

        return mapToDto(customer);
    }

    public void updateCustomerFields(
            Customer customer,
            CustomerInputDto input
    ) {
        customer.setDisplayName(input.getDisplayName());
        customer.setRegistrationChannel(input.getRegistrationChannel());
        customer.setRegistrationBranchCode(input.getRegistrationBranchCode());
        customer.setEmail(input.getEmail());
        customer.setPhoneNumber(input.getPhoneNumber());
        customer.setCountry(input.getCountry());
        customer.setCity(input.getCity());
        customer.setAddress(input.getAddress());
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
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

    private CustomerDto mapToDto(Customer customer) {
        // We will handle polymorphic mapping separately.
        return null;
    }
}