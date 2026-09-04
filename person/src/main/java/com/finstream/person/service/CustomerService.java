package com.finstream.person.service;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.regChannel.RegistrationChannel;
import com.finstream.person.repository.ICustomerRepository;
import com.finstream.person.status.CustomerStatus;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public  abstract class CustomerService {

    private final ICustomerRepository customerRepository;

    public CustomerService(
            ICustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    // ========================================================================
    // READ
    // ========================================================================

    public Optional<Customer> findByEmail(String email) {

        return customerRepository.findByEmail(email);
    }

    public Optional<Customer> findByPhoneNumber(String phoneNumber) {

        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Customer> findByDisplayName(
            String displayName
    ) {

        return customerRepository
                .findByDisplayNameContainingIgnoreCase(displayName);
    }

    public List<Customer> findByStatus(
            CustomerStatus status
    ) {

        return customerRepository.findByStatus(status);
    }

    public List<Customer> findByRegistrationChannel(
            RegistrationChannel registrationChannel
    ) {

        return customerRepository.findByRegistrationChannel(
                registrationChannel
        );
    }

    public List<Customer> findByRegistrationBranchCode(
            String registrationBranchCode
    ) {

        return customerRepository.findByRegistrationBranchCode(
                registrationBranchCode
        );
    }

    public List<Customer> findByCountry(
            String country
    ) {

        return customerRepository.findByCountry(country);
    }

    public List<Customer> findByCity(
            String city
    ) {

        return customerRepository.findByCity(city);
    }

    public List<Customer> findByCountryAndCity(
            String country,
            String city
    ) {

        return customerRepository.findByCountryAndCity(
                country,
                city
        );
    }

    public List<Customer> findByStatusAndRegistrationBranchCode(
            CustomerStatus status,
            String registrationBranchCode
    ) {

        return customerRepository.findByStatusAndRegistrationBranchCode(
                status,
                registrationBranchCode
        );
    }
}