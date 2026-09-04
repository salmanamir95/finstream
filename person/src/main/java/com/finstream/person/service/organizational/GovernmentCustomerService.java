package com.finstream.person.service.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.GovernmentCustomer;
import com.finstream.person.governmentstatus.GovernmentLevel;
import com.finstream.person.repository.organizational.GovernmentCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GovernmentCustomerService
        extends OrganizationalCustomerService {

    private final GovernmentCustomerRepository
            governmentCustomerRepository;

    public GovernmentCustomerService(
            GovernmentCustomerRepository governmentCustomerRepository
    ) {
        super(governmentCustomerRepository);

        this.governmentCustomerRepository =
                governmentCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public GovernmentCustomer createCustomer(
            GovernmentCustomer customer
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

        validateGovernmentId(customer);

        return governmentCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - GOVERNMENT CUSTOMER
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<GovernmentCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return governmentCustomerRepository.findById(
                customerId
        );
    }

    @Transactional(readOnly = true)
    public Optional<GovernmentCustomer> findByGovernmentId(
            String governmentId
    ) {

        if (governmentId == null || governmentId.isBlank()) {
            return Optional.empty();
        }

        return governmentCustomerRepository.findByGovernmentId(
                governmentId.trim()
        );
    }

    @Transactional(readOnly = true)
    public List<GovernmentCustomer>
    findByGovernmentAgency(
            String governmentAgency
    ) {

        if (governmentAgency == null
                || governmentAgency.isBlank()) {

            return List.of();
        }

        return governmentCustomerRepository
                .findByGovernmentAgencyContainingIgnoreCase(
                        governmentAgency.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<GovernmentCustomer>
    findByGovernmentLevel(
            GovernmentLevel governmentLevel
    ) {

        if (governmentLevel == null) {
            return List.of();
        }

        return governmentCustomerRepository
                .findByGovernmentLevel(
                        governmentLevel
                );
    }

    @Transactional(readOnly = true)
    public List<GovernmentCustomer>
    findByDepartmentName(
            String departmentName
    ) {

        if (departmentName == null
                || departmentName.isBlank()) {

            return List.of();
        }

        return governmentCustomerRepository
                .findByDepartmentNameContainingIgnoreCase(
                        departmentName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<GovernmentCustomer>
    findByJurisdiction(
            String jurisdiction
    ) {

        if (jurisdiction == null
                || jurisdiction.isBlank()) {

            return List.of();
        }

        return governmentCustomerRepository
                .findByJurisdiction(
                        jurisdiction.trim()
                );
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public GovernmentCustomer updateCustomer(
            GovernmentCustomer customer
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

        if (!governmentCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        validateGovernmentId(customer);

        return governmentCustomerRepository.save(customer);
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

        if (!governmentCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        governmentCustomerRepository.deleteById(
                customerId
        );
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(
            Long customerId
    ) {

        return customerId != null
                && governmentCustomerRepository
                        .existsById(customerId);
    }

    @Transactional(readOnly = true)
    public boolean existsByGovernmentId(
            String governmentId
    ) {

        if (governmentId == null
                || governmentId.isBlank()) {

            return false;
        }

        return governmentCustomerRepository
                .existsByGovernmentId(
                        governmentId.trim()
                );
    }

    // ========================================================================
    // VALIDATION
    // ========================================================================

    private void validateGovernmentId(
            GovernmentCustomer customer
    ) {

        String governmentId = customer.getGovernmentId();

        if (governmentId == null || governmentId.isBlank()) {
            return;
        }

        Optional<GovernmentCustomer> existingCustomer =
                governmentCustomerRepository
                        .findByGovernmentId(
                                governmentId.trim()
                        );

        if (existingCustomer.isPresent()
                && !existingCustomer
                        .get()
                        .getCustomerId()
                        .equals(customer.getCustomerId())) {

            throw new IllegalArgumentException(
                    "Another government customer already exists "
                            + "with government ID: "
                            + governmentId
            );
        }
    }
}
