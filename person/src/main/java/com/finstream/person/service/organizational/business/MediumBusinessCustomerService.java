package com.finstream.person.service.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.MediumBusinessCustomer;
import com.finstream.person.repository.organizational.business.MediumBusinessCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MediumBusinessCustomerService
        extends BusinessCustomerService {

    private final MediumBusinessCustomerRepository
            mediumBusinessCustomerRepository;

    public MediumBusinessCustomerService(
            MediumBusinessCustomerRepository mediumBusinessCustomerRepository
    ) {
        super(mediumBusinessCustomerRepository);

        this.mediumBusinessCustomerRepository =
                mediumBusinessCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public MediumBusinessCustomer createCustomer(
            MediumBusinessCustomer customer
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

        return mediumBusinessCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - MEDIUM BUSINESS
    // ========================================================================

    @Transactional(readOnly = true)
    public MediumBusinessCustomer findByIdOrNull(
            Long customerId
    ) {

        if (customerId == null) {
            return null;
        }

        return mediumBusinessCustomerRepository
                .findById(customerId)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<MediumBusinessCustomer>
    findByNumberOfBranchesGreaterThan(
            Integer numberOfBranches
    ) {

        if (numberOfBranches == null) {
            return List.of();
        }

        return mediumBusinessCustomerRepository
                .findByNumberOfBranchesGreaterThan(
                        numberOfBranches
                );
    }

    @Transactional(readOnly = true)
    public List<MediumBusinessCustomer>
    findByNumberOfDepartmentsGreaterThan(
            Integer numberOfDepartments
    ) {

        if (numberOfDepartments == null) {
            return List.of();
        }

        return mediumBusinessCustomerRepository
                .findByNumberOfDepartmentsGreaterThan(
                        numberOfDepartments
                );
    }

    @Transactional(readOnly = true)
    public List<MediumBusinessCustomer>
    findByChiefExecutiveName(
            String chiefExecutiveName
    ) {

        if (chiefExecutiveName == null
                || chiefExecutiveName.isBlank()) {

            return List.of();
        }

        return mediumBusinessCustomerRepository
                .findByChiefExecutiveNameContainingIgnoreCase(
                        chiefExecutiveName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<MediumBusinessCustomer>
    findByFinanceManagerName(
            String financeManagerName
    ) {

        if (financeManagerName == null
                || financeManagerName.isBlank()) {

            return List.of();
        }

        return mediumBusinessCustomerRepository
                .findByFinanceManagerNameContainingIgnoreCase(
                        financeManagerName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<MediumBusinessCustomer>
    findByAuditedFinancials() {

        return mediumBusinessCustomerRepository
                .findByAuditedFinancialsTrue();
    }

    @Transactional(readOnly = true)
    public List<MediumBusinessCustomer>
    findByNotAuditedFinancials() {

        return mediumBusinessCustomerRepository
                .findByAuditedFinancialsFalse();
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public MediumBusinessCustomer updateCustomer(
            MediumBusinessCustomer customer
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

        if (!mediumBusinessCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        return mediumBusinessCustomerRepository.save(customer);
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

        if (!mediumBusinessCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        mediumBusinessCustomerRepository.deleteById(customerId);
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(Long customerId) {

        return customerId != null
                && mediumBusinessCustomerRepository
                        .existsById(customerId);
    }
}
