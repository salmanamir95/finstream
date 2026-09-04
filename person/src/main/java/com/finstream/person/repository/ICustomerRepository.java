package com.finstream.person.repository;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.regChannel.RegistrationChannel;
import com.finstream.person.status.CustomerStatus;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ICustomerRepository {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhoneNumber(String phoneNumber);

    List<Customer> findByDisplayNameContainingIgnoreCase(
            String displayName
    );

    List<Customer> findByStatus(
            CustomerStatus status
    );

    List<Customer> findByRegistrationChannel(
            RegistrationChannel registrationChannel
    );

    List<Customer> findByRegistrationBranchCode(
            String registrationBranchCode
    );

    List<Customer> findByCountry(
            String country
    );

    List<Customer> findByCity(
            String city
    );

    List<Customer> findByCountryAndCity(
            String country,
            String city
    );

    List<Customer> findByStatusAndRegistrationBranchCode(
            CustomerStatus status,
            String registrationBranchCode
    );
}
