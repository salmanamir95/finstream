package com.finstream.person.repository;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.regChannel.RegistrationChannel;
import com.finstream.person.status.CustomerStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ICustomerRepositoryImpl implements ICustomerRepository {

    @PersistenceContext
    protected EntityManager entityManager;

    // ---------------------------------------------------------------
    // Single-result lookups
    // ---------------------------------------------------------------

    @Override
    public Optional<Customer> findByEmail(String email) {
        if (email == null || email.isBlank()) {
            return Optional.empty();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE LOWER(c.email) = LOWER(:email)",
                Customer.class
        );
        query.setParameter("email", email.trim());
        query.setMaxResults(1);

        return query.getResultStream().findFirst();
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return Optional.empty();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.phoneNumber = :phoneNumber",
                Customer.class
        );
        query.setParameter("phoneNumber", phoneNumber.trim());
        query.setMaxResults(1);

        return query.getResultStream().findFirst();
    }

    // ---------------------------------------------------------------
    // Multi-result lookups
    // ---------------------------------------------------------------

    @Override
    public List<Customer> findByDisplayNameContainingIgnoreCase(String displayName) {
        if (displayName == null || displayName.isBlank()) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE LOWER(c.displayName) LIKE LOWER(:pattern)",
                Customer.class
        );
        query.setParameter("pattern", "%" + displayName.trim() + "%");

        return query.getResultList();
    }

    @Override
    public List<Customer> findByStatus(CustomerStatus status) {
        if (status == null) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.status = :status",
                Customer.class
        );
        query.setParameter("status", status);

        return query.getResultList();
    }

    @Override
    public List<Customer> findByRegistrationChannel(RegistrationChannel registrationChannel) {
        if (registrationChannel == null) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.registrationChannel = :channel",
                Customer.class
        );
        query.setParameter("channel", registrationChannel);

        return query.getResultList();
    }

    @Override
    public List<Customer> findByRegistrationBranchCode(String registrationBranchCode) {
        if (registrationBranchCode == null || registrationBranchCode.isBlank()) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.registrationBranchCode = :branchCode",
                Customer.class
        );
        query.setParameter("branchCode", registrationBranchCode.trim());

        return query.getResultList();
    }

    @Override
    public List<Customer> findByCountry(String country) {
        if (country == null || country.isBlank()) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.country = :country",
                Customer.class
        );
        query.setParameter("country", country.trim());

        return query.getResultList();
    }

    @Override
    public List<Customer> findByCity(String city) {
        if (city == null || city.isBlank()) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.city = :city",
                Customer.class
        );
        query.setParameter("city", city.trim());

        return query.getResultList();
    }

    @Override
    public List<Customer> findByCountryAndCity(String country, String city) {
        if (country == null || city == null) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.country = :country AND c.city = :city",
                Customer.class
        );
        query.setParameter("country", country.trim());
        query.setParameter("city", city.trim());

        return query.getResultList();
    }

    @Override
    public List<Customer> findByStatusAndRegistrationBranchCode(
            CustomerStatus status,
            String registrationBranchCode
    ) {
        if (status == null || registrationBranchCode == null || registrationBranchCode.isBlank()) {
            return Collections.emptyList();
        }

        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM Customer c WHERE c.status = :status AND c.registrationBranchCode = :branchCode",
                Customer.class
        );
        query.setParameter("status", status);
        query.setParameter("branchCode", registrationBranchCode.trim());

        return query.getResultList();
    }
}