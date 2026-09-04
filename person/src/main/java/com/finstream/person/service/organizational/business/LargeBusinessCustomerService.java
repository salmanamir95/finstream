package com.finstream.person.service.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.LargeBusinessCustomer;
import com.finstream.person.repository.organizational.business.LargeBusinessCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LargeBusinessCustomerService
        extends BusinessCustomerService {

    private final LargeBusinessCustomerRepository
            largeBusinessCustomerRepository;

    public LargeBusinessCustomerService(
            LargeBusinessCustomerRepository largeBusinessCustomerRepository
    ) {
        super(largeBusinessCustomerRepository);

        this.largeBusinessCustomerRepository =
                largeBusinessCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public LargeBusinessCustomer createCustomer(
            LargeBusinessCustomer customer
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

        return largeBusinessCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - LARGE BUSINESS
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<LargeBusinessCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return largeBusinessCustomerRepository.findById(
                customerId
        );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByNumberOfBranchesGreaterThan(
            Integer numberOfBranches
    ) {

        if (numberOfBranches == null) {
            return List.of();
        }

        return largeBusinessCustomerRepository
                .findByNumberOfBranchesGreaterThan(
                        numberOfBranches
                );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByNumberOfSubsidiariesGreaterThan(
            Integer numberOfSubsidiaries
    ) {

        if (numberOfSubsidiaries == null) {
            return List.of();
        }

        return largeBusinessCustomerRepository
                .findByNumberOfSubsidiariesGreaterThan(
                        numberOfSubsidiaries
                );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByChiefExecutiveName(
            String chiefExecutiveName
    ) {

        if (chiefExecutiveName == null
                || chiefExecutiveName.isBlank()) {

            return List.of();
        }

        return largeBusinessCustomerRepository
                .findByChiefExecutiveNameContainingIgnoreCase(
                        chiefExecutiveName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByChiefFinancialOfficer(
            String chiefFinancialOfficer
    ) {

        if (chiefFinancialOfficer == null
                || chiefFinancialOfficer.isBlank()) {

            return List.of();
        }

        return largeBusinessCustomerRepository
                .findByChiefFinancialOfficerContainingIgnoreCase(
                        chiefFinancialOfficer.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByCorporateGroupName(
            String corporateGroupName
    ) {

        if (corporateGroupName == null
                || corporateGroupName.isBlank()) {

            return List.of();
        }

        return largeBusinessCustomerRepository
                .findByCorporateGroupNameContainingIgnoreCase(
                        corporateGroupName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByPubliclyListed() {

        return largeBusinessCustomerRepository
                .findByPubliclyListedTrue();
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByNotPubliclyListed() {

        return largeBusinessCustomerRepository
                .findByPubliclyListedFalse();
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByStockExchange(
            String stockExchange
    ) {

        if (stockExchange == null
                || stockExchange.isBlank()) {

            return List.of();
        }

        return largeBusinessCustomerRepository
                .findByStockExchange(
                        stockExchange.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByAuditedFinancials() {

        return largeBusinessCustomerRepository
                .findByAuditedFinancialsTrue();
    }

    @Transactional(readOnly = true)
    public List<LargeBusinessCustomer>
    findByNotAuditedFinancials() {

        return largeBusinessCustomerRepository
                .findByAuditedFinancialsFalse();
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public LargeBusinessCustomer updateCustomer(
            LargeBusinessCustomer customer
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

        if (!largeBusinessCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        return largeBusinessCustomerRepository.save(customer);
    }

    // ========================================================================
    // DELETE
    // ========================================================================

    public void deleteCustomer(Long customerId) {

        if (customerId == null) {
            throw new IllegalArgumentException(
                    "Customer ID must not be null"
            );
        }

        if (!largeBusinessCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        largeBusinessCustomerRepository.deleteById(customerId);
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(Long customerId) {

        return customerId != null
                && largeBusinessCustomerRepository
                        .existsById(customerId);
    }
}
