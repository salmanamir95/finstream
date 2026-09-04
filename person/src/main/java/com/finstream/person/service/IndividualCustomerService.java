package com.finstream.person.service;

import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.gender.Gender;
import com.finstream.person.repository.IndividualCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IndividualCustomerService extends CustomerService {

    private final IndividualCustomerRepository individualCustomerRepository;

    public IndividualCustomerService(
            IndividualCustomerRepository individualCustomerRepository
    ) {
        super(individualCustomerRepository);
        this.individualCustomerRepository = individualCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public IndividualCustomer createCustomer(
            IndividualCustomer customer
    ) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer must not be null"
            );
        }

        if (customer.getCustomerId() != null) {
            throw new IllegalArgumentException(
                    "New customer must not already have an ID"
            );
        }

        if (customer.getNationalId() != null
                && individualCustomerRepository.existsByNationalId(
                        customer.getNationalId()
                )) {

            throw new IllegalArgumentException(
                    "Customer with national ID already exists: "
                            + customer.getNationalId()
            );
        }

        return individualCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - INDIVIDUAL CUSTOMER
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<IndividualCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return individualCustomerRepository.findById(customerId);
    }

    @Transactional(readOnly = true)
    public Optional<IndividualCustomer> findByNationalId(
            String nationalId
    ) {

        if (nationalId == null || nationalId.isBlank()) {
            return Optional.empty();
        }

        return individualCustomerRepository.findByNationalId(
                nationalId.trim()
        );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByFirstName(
            String firstName
    ) {

        if (firstName == null || firstName.isBlank()) {
            return List.of();
        }

        return individualCustomerRepository
                .findByFirstNameContainingIgnoreCase(
                        firstName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByLastName(
            String lastName
    ) {

        if (lastName == null || lastName.isBlank()) {
            return List.of();
        }

        return individualCustomerRepository
                .findByLastNameContainingIgnoreCase(
                        lastName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByName(
            String firstName,
            String lastName
    ) {

        if (firstName == null
                || firstName.isBlank()
                || lastName == null
                || lastName.isBlank()) {

            return List.of();
        }

        return individualCustomerRepository
                .findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
                        firstName.trim(),
                        lastName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByNationality(
            String nationality
    ) {

        if (nationality == null || nationality.isBlank()) {
            return List.of();
        }

        return individualCustomerRepository.findByNationality(
                nationality.trim()
        );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByGender(
            Gender gender
    ) {

        if (gender == null) {
            return List.of();
        }

        return individualCustomerRepository.findByGender(gender);
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByOccupation(
            String occupation
    ) {

        if (occupation == null || occupation.isBlank()) {
            return List.of();
        }

        return individualCustomerRepository.findByOccupation(
                occupation.trim()
        );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByEmployerName(
            String employerName
    ) {

        if (employerName == null || employerName.isBlank()) {
            return List.of();
        }

        return individualCustomerRepository.findByEmployerName(
                employerName.trim()
        );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByDateOfBirth(
            LocalDate dateOfBirth
    ) {

        if (dateOfBirth == null) {
            return List.of();
        }

        return individualCustomerRepository.findByDateOfBirth(
                dateOfBirth
        );
    }

    @Transactional(readOnly = true)
    public List<IndividualCustomer> findByDateOfBirthBetween(
            LocalDate startDate,
            LocalDate endDate
    ) {

        if (startDate == null || endDate == null) {
            return List.of();
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start date must not be after end date"
            );
        }

        return individualCustomerRepository
                .findByDateOfBirthBetween(
                        startDate,
                        endDate
                );
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public IndividualCustomer updateCustomer(
            IndividualCustomer customer
    ) {

        if (customer == null) {
            throw new IllegalArgumentException(
                    "Customer must not be null"
            );
        }

        if (customer.getCustomerId() == null) {
            throw new IllegalArgumentException(
                    "Customer ID is required when updating a customer"
            );
        }

        Long customerId = customer.getCustomerId();

        if (!individualCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        Optional<IndividualCustomer> existingCustomer =
                individualCustomerRepository.findById(customerId);

        if (existingCustomer.isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        String nationalId = customer.getNationalId();

        if (nationalId != null
                && !nationalId.isBlank()) {

            Optional<IndividualCustomer> customerWithNationalId =
                    individualCustomerRepository.findByNationalId(
                            nationalId.trim()
                    );

            if (customerWithNationalId.isPresent()
                    && !customerWithNationalId
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another customer already exists with national ID: "
                                + nationalId
                );
            }
        }

        return individualCustomerRepository.save(customer);
    }

    // ========================================================================
    // DELETE
    // ========================================================================

    public void deleteCustomer(
            Long customerId
    ) {

        if (customerId == null) {
            throw new IllegalArgumentException(
                    "Customer ID must not be null"
            );
        }

        if (!individualCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        individualCustomerRepository.deleteById(customerId);
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(
            Long customerId
    ) {

        if (customerId == null) {
            return false;
        }

        return individualCustomerRepository.existsById(
                customerId
        );
    }

    @Transactional(readOnly = true)
    public boolean existsByNationalId(
            String nationalId
    ) {

        if (nationalId == null || nationalId.isBlank()) {
            return false;
        }

        return individualCustomerRepository.existsByNationalId(
                nationalId.trim()
        );
    }
}
