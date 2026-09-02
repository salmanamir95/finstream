package com.finstream.person.service;

import org.springframework.stereotype.Service;

import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.dto.CustomerInputDto;
import com.finstream.person.dto.IndividualCustomerDto;
import com.finstream.person.mapper.IndividualCustomerMapper;
import com.finstream.person.repository.IndividualCustomerRepository;

@Service
public class IndividualCustomerService {

    private final IndividualCustomerRepository repository;
    private final IndividualCustomerMapper mapper;
    private final CustomerService customerService;

    public IndividualCustomerService(
            IndividualCustomerRepository repository,
            IndividualCustomerMapper mapper,
            CustomerService customerService
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.customerService = customerService;
    }

    public IndividualCustomerDto createCustomer(
            IndividualCustomerDto input
    ) {
        IndividualCustomer customer = mapper.toEntity(input);

        IndividualCustomer savedCustomer = repository.save(customer);

        return mapper.toDto(savedCustomer);
    }

    public IndividualCustomerDto getCustomer(Long customerId) {

        IndividualCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Individual customer not found with id: "
                                        + customerId
                        )
                );

        return mapper.toDto(customer);
    }

    public IndividualCustomerDto updateCustomer(
            Long customerId,
            IndividualCustomerDto input
    ) {

        IndividualCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Individual customer not found with id: "
                                        + customerId
                        )
                );

        // Update Customer-level fields
        customerService.updateCustomerFields(customer, input);

        // Update IndividualCustomer-level fields
        mapper.updateEntity(input, customer);

        IndividualCustomer updatedCustomer =
                repository.save(customer);

        return mapper.toDto(updatedCustomer);
    }

    public void deleteCustomer(Long customerId) {

        IndividualCustomer customer = repository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Individual customer not found with id: "
                                        + customerId
                        )
                );

        repository.delete(customer);
    }
}