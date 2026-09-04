package com.finstream.person.service.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.FinancialInstitutionCustomer;
import com.finstream.person.finStatus.FinancialInstitutionType;
import com.finstream.person.repository.organizational.FinancialInstitutionCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FinancialInstitutionCustomerService
        extends OrganizationalCustomerService {

    private final FinancialInstitutionCustomerRepository
            financialInstitutionCustomerRepository;

    public FinancialInstitutionCustomerService(
            FinancialInstitutionCustomerRepository
                    financialInstitutionCustomerRepository
    ) {
        super(financialInstitutionCustomerRepository);

        this.financialInstitutionCustomerRepository =
                financialInstitutionCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public FinancialInstitutionCustomer createCustomer(
            FinancialInstitutionCustomer customer
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

        return financialInstitutionCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - FINANCIAL INSTITUTION
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<FinancialInstitutionCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return financialInstitutionCustomerRepository.findById(
                customerId
        );
    }

    @Transactional(readOnly = true)
    public Optional<FinancialInstitutionCustomer> findByLicenseNumber(
            String licenseNumber
    ) {

        if (licenseNumber == null || licenseNumber.isBlank()) {
            return Optional.empty();
        }

        return financialInstitutionCustomerRepository
                .findByLicenseNumber(
                        licenseNumber.trim()
                );
    }

    @Transactional(readOnly = true)
    public Optional<FinancialInstitutionCustomer> findBySwiftCode(
            String swiftCode
    ) {

        if (swiftCode == null || swiftCode.isBlank()) {
            return Optional.empty();
        }

        return financialInstitutionCustomerRepository
                .findBySwiftCode(
                        swiftCode.trim()
                );
    }

    @Transactional(readOnly = true)
    public Optional<FinancialInstitutionCustomer> findByBicCode(
            String bicCode
    ) {

        if (bicCode == null || bicCode.isBlank()) {
            return Optional.empty();
        }

        return financialInstitutionCustomerRepository
                .findByBicCode(
                        bicCode.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<FinancialInstitutionCustomer>
    findByFinancialInstitutionType(
            FinancialInstitutionType type
    ) {

        if (type == null) {
            return List.of();
        }

        return financialInstitutionCustomerRepository
                .findByFinancialInstitutionType(type);
    }

    @Transactional(readOnly = true)
    public List<FinancialInstitutionCustomer>
    findByRegulatoryAuthority(
            String regulatoryAuthority
    ) {

        if (regulatoryAuthority == null
                || regulatoryAuthority.isBlank()) {

            return List.of();
        }

        return financialInstitutionCustomerRepository
                .findByRegulatoryAuthority(
                        regulatoryAuthority.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<FinancialInstitutionCustomer>
    findByLicenseCountry(
            String licenseCountry
    ) {

        if (licenseCountry == null
                || licenseCountry.isBlank()) {

            return List.of();
        }

        return financialInstitutionCustomerRepository
                .findByLicenseCountry(
                        licenseCountry.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<FinancialInstitutionCustomer>
    findByInternationallyOperating() {

        return financialInstitutionCustomerRepository
                .findByInternationallyOperatingTrue();
    }

    @Transactional(readOnly = true)
    public List<FinancialInstitutionCustomer>
    findByBranchCountGreaterThan(
            Integer branchCount
    ) {

        if (branchCount == null) {
            return List.of();
        }

        return financialInstitutionCustomerRepository
                .findByBranchCountGreaterThan(branchCount);
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public FinancialInstitutionCustomer updateCustomer(
            FinancialInstitutionCustomer customer
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

        if (!financialInstitutionCustomerRepository
                .existsById(customerId)) {

            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        validateUniqueFields(customer);

        return financialInstitutionCustomerRepository.save(customer);
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

        if (!financialInstitutionCustomerRepository
                .existsById(customerId)) {

            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        financialInstitutionCustomerRepository.deleteById(
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
                && financialInstitutionCustomerRepository
                        .existsById(customerId);
    }

    @Transactional(readOnly = true)
    public boolean existsByLicenseNumber(
            String licenseNumber
    ) {

        if (licenseNumber == null
                || licenseNumber.isBlank()) {

            return false;
        }

        return financialInstitutionCustomerRepository
                .existsByLicenseNumber(
                        licenseNumber.trim()
                );
    }

    @Transactional(readOnly = true)
    public boolean existsBySwiftCode(
            String swiftCode
    ) {

        if (swiftCode == null || swiftCode.isBlank()) {
            return false;
        }

        return financialInstitutionCustomerRepository
                .existsBySwiftCode(
                        swiftCode.trim()
                );
    }

    // ========================================================================
    // VALIDATION
    // ========================================================================

    private void validateUniqueFields(
            FinancialInstitutionCustomer customer
    ) {

        Long customerId = customer.getCustomerId();

        String licenseNumber = customer.getLicenseNumber();

        if (licenseNumber != null
                && !licenseNumber.isBlank()) {

            Optional<FinancialInstitutionCustomer>
                    existingByLicense =
                    financialInstitutionCustomerRepository
                            .findByLicenseNumber(
                                    licenseNumber.trim()
                            );

            if (existingByLicense.isPresent()
                    && !existingByLicense
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another financial institution already exists "
                                + "with license number: "
                                + licenseNumber
                );
            }
        }

        String swiftCode = customer.getSwiftCode();

        if (swiftCode != null
                && !swiftCode.isBlank()) {

            Optional<FinancialInstitutionCustomer>
                    existingBySwift =
                    financialInstitutionCustomerRepository
                            .findBySwiftCode(
                                    swiftCode.trim()
                            );

            if (existingBySwift.isPresent()
                    && !existingBySwift
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another financial institution already exists "
                                + "with SWIFT code: "
                                + swiftCode
                );
            }
        }

        String bicCode = customer.getBicCode();

        if (bicCode != null
                && !bicCode.isBlank()) {

            Optional<FinancialInstitutionCustomer>
                    existingByBic =
                    financialInstitutionCustomerRepository
                            .findByBicCode(
                                    bicCode.trim()
                            );

            if (existingByBic.isPresent()
                    && !existingByBic
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another financial institution already exists "
                                + "with BIC code: "
                                + bicCode
                );
            }
        }
    }
}

