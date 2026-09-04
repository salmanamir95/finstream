package com.finstream.person.service.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.SmallBusinessCustomer;
import com.finstream.person.repository.organizational.business.SmallBusinessCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SmallBusinessCustomerService
        extends BusinessCustomerService {

    private final SmallBusinessCustomerRepository
            smallBusinessCustomerRepository;

    public SmallBusinessCustomerService(
            SmallBusinessCustomerRepository smallBusinessCustomerRepository
    ) {
        super(smallBusinessCustomerRepository);

        this.smallBusinessCustomerRepository =
                smallBusinessCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public SmallBusinessCustomer createCustomer(
            SmallBusinessCustomer customer
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

        validateUniqueFields(customer);

        return smallBusinessCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - SMALL BUSINESS
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<SmallBusinessCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return smallBusinessCustomerRepository.findById(customerId);
    }

    @Transactional(readOnly = true)
    public Optional<SmallBusinessCustomer> findByOwnerNationalId(
            String ownerNationalId
    ) {

        if (ownerNationalId == null || ownerNationalId.isBlank()) {
            return Optional.empty();
        }

        return smallBusinessCustomerRepository
                .findByOwnerNationalId(
                        ownerNationalId.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<SmallBusinessCustomer> findByOwnerName(
            String ownerName
    ) {

        if (ownerName == null || ownerName.isBlank()) {
            return List.of();
        }

        return smallBusinessCustomerRepository
                .findByOwnerNameContainingIgnoreCase(
                        ownerName.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<SmallBusinessCustomer> findByOwnerManaged() {
        return smallBusinessCustomerRepository
                .findByOwnerManagedTrue();
    }

    @Transactional(readOnly = true)
    public List<SmallBusinessCustomer> findByNotOwnerManaged() {
        return smallBusinessCustomerRepository
                .findByOwnerManagedFalse();
    }

    @Transactional(readOnly = true)
    public Optional<SmallBusinessCustomer> findByBusinessLicenseNumber(
            String businessLicenseNumber
    ) {

        if (businessLicenseNumber == null
                || businessLicenseNumber.isBlank()) {

            return Optional.empty();
        }

        return smallBusinessCustomerRepository
                .findByBusinessLicenseNumber(
                        businessLicenseNumber.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<SmallBusinessCustomer>
    findByPrimaryProductOrService(
            String primaryProductOrService
    ) {

        if (primaryProductOrService == null
                || primaryProductOrService.isBlank()) {

            return List.of();
        }

        return smallBusinessCustomerRepository
                .findByPrimaryProductOrServiceContainingIgnoreCase(
                        primaryProductOrService.trim()
                );
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public SmallBusinessCustomer updateCustomer(
            SmallBusinessCustomer customer
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

        if (!smallBusinessCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        validateUniqueFields(customer);

        return smallBusinessCustomerRepository.save(customer);
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

        if (!smallBusinessCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        smallBusinessCustomerRepository.deleteById(customerId);
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(Long customerId) {
        return customerId != null
                && smallBusinessCustomerRepository.existsById(customerId);
    }

    @Transactional(readOnly = true)
    public boolean existsByOwnerNationalId(String ownerNationalId) {

        if (ownerNationalId == null || ownerNationalId.isBlank()) {
            return false;
        }

        return smallBusinessCustomerRepository
                .existsByOwnerNationalId(
                        ownerNationalId.trim()
                );
    }

    @Transactional(readOnly = true)
    public boolean existsByBusinessLicenseNumber(
            String businessLicenseNumber
    ) {

        if (businessLicenseNumber == null
                || businessLicenseNumber.isBlank()) {

            return false;
        }

        return smallBusinessCustomerRepository
                .existsByBusinessLicenseNumber(
                        businessLicenseNumber.trim()
                );
    }

    // ========================================================================
    // VALIDATION
    // ========================================================================

    private void validateUniqueFields(
            SmallBusinessCustomer customer
    ) {

        Long customerId = customer.getCustomerId();

        String ownerNationalId = customer.getOwnerNationalId();

        if (ownerNationalId != null
                && !ownerNationalId.isBlank()) {

            Optional<SmallBusinessCustomer> existingByOwnerId =
                    smallBusinessCustomerRepository
                            .findByOwnerNationalId(
                                    ownerNationalId.trim()
                            );

            if (existingByOwnerId.isPresent()
                    && !existingByOwnerId
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another small business customer already exists "
                                + "with owner national ID: "
                                + ownerNationalId
                );
            }
        }

        String businessLicenseNumber =
                customer.getBusinessLicenseNumber();

        if (businessLicenseNumber != null
                && !businessLicenseNumber.isBlank()) {

            Optional<SmallBusinessCustomer> existingByLicense =
                    smallBusinessCustomerRepository
                            .findByBusinessLicenseNumber(
                                    businessLicenseNumber.trim()
                            );

            if (existingByLicense.isPresent()
                    && !existingByLicense
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another small business customer already exists "
                                + "with business license number: "
                                + businessLicenseNumber
                );
            }
        }
    }
}
