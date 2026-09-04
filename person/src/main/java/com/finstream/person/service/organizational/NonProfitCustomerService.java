package com.finstream.person.service.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.NonProfitCustomer;
import com.finstream.person.nonprofit.NonProfitType;
import com.finstream.person.repository.organizational.NonProfitCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NonProfitCustomerService
        extends OrganizationalCustomerService {

    private final NonProfitCustomerRepository
            nonProfitCustomerRepository;

    public NonProfitCustomerService(
            NonProfitCustomerRepository nonProfitCustomerRepository
    ) {
        super(nonProfitCustomerRepository);

        this.nonProfitCustomerRepository =
                nonProfitCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public NonProfitCustomer createCustomer(
            NonProfitCustomer customer
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

        validateTaxExemptionNumber(customer);

        return nonProfitCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - NON-PROFIT CUSTOMER
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<NonProfitCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return nonProfitCustomerRepository.findById(
                customerId
        );
    }

    @Transactional(readOnly = true)
    public Optional<NonProfitCustomer> findByTaxExemptionNumber(
            String taxExemptionNumber
    ) {

        if (taxExemptionNumber == null
                || taxExemptionNumber.isBlank()) {

            return Optional.empty();
        }

        return nonProfitCustomerRepository
                .findByTaxExemptionNumber(
                        taxExemptionNumber.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<NonProfitCustomer> findByNonProfitType(
            NonProfitType nonProfitType
    ) {

        if (nonProfitType == null) {
            return List.of();
        }

        return nonProfitCustomerRepository
                .findByNonProfitType(nonProfitType);
    }

    @Transactional(readOnly = true)
    public List<NonProfitCustomer>
    findByRegistrationAuthority(
            String registrationAuthority
    ) {

        if (registrationAuthority == null
                || registrationAuthority.isBlank()) {

            return List.of();
        }

        return nonProfitCustomerRepository
                .findByRegistrationAuthorityContainingIgnoreCase(
                        registrationAuthority.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<NonProfitCustomer> findByMissionStatement(
            String missionStatement
    ) {

        if (missionStatement == null
                || missionStatement.isBlank()) {

            return List.of();
        }

        return nonProfitCustomerRepository
                .findByMissionStatementContainingIgnoreCase(
                        missionStatement.trim()
                );
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public NonProfitCustomer updateCustomer(
            NonProfitCustomer customer
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

        if (!nonProfitCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        validateTaxExemptionNumber(customer);

        return nonProfitCustomerRepository.save(customer);
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

        if (!nonProfitCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        nonProfitCustomerRepository.deleteById(customerId);
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(
            Long customerId
    ) {

        return customerId != null
                && nonProfitCustomerRepository.existsById(
                        customerId
                );
    }

    @Transactional(readOnly = true)
    public boolean existsByTaxExemptionNumber(
            String taxExemptionNumber
    ) {

        if (taxExemptionNumber == null
                || taxExemptionNumber.isBlank()) {

            return false;
        }

        return nonProfitCustomerRepository
                .existsByTaxExemptionNumber(
                        taxExemptionNumber.trim()
                );
    }

    // ========================================================================
    // VALIDATION
    // ========================================================================

    private void validateTaxExemptionNumber(
            NonProfitCustomer customer
    ) {

        String taxExemptionNumber =
                customer.getTaxExemptionNumber();

        if (taxExemptionNumber == null
                || taxExemptionNumber.isBlank()) {

            return;
        }

        Optional<NonProfitCustomer> existingCustomer =
                nonProfitCustomerRepository
                        .findByTaxExemptionNumber(
                                taxExemptionNumber.trim()
                        );

        if (existingCustomer.isPresent()
                && !existingCustomer
                        .get()
                        .getCustomerId()
                        .equals(customer.getCustomerId())) {

            throw new IllegalArgumentException(
                    "Another non-profit customer already exists "
                            + "with tax exemption number: "
                            + taxExemptionNumber
            );
        }
    }
}
