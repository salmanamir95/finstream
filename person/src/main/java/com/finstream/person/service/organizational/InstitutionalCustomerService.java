
package com.finstream.person.service.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.InstitutionalCustomer;
import com.finstream.person.instituitions.InstitutionType;
import com.finstream.person.repository.organizational.InstitutionalCustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstitutionalCustomerService
        extends OrganizationalCustomerService {

    private final InstitutionalCustomerRepository
            institutionalCustomerRepository;

    public InstitutionalCustomerService(
            InstitutionalCustomerRepository institutionalCustomerRepository
    ) {
        super(institutionalCustomerRepository);

        this.institutionalCustomerRepository =
                institutionalCustomerRepository;
    }

    // ========================================================================
    // CREATE
    // ========================================================================

    public InstitutionalCustomer createCustomer(
            InstitutionalCustomer customer
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

        return institutionalCustomerRepository.save(customer);
    }

    // ========================================================================
    // READ - INSTITUTIONAL CUSTOMER
    // ========================================================================

    @Transactional(readOnly = true)
    public Optional<InstitutionalCustomer> findById(
            Long customerId
    ) {

        if (customerId == null) {
            return Optional.empty();
        }

        return institutionalCustomerRepository.findById(customerId);
    }

    @Transactional(readOnly = true)
    public Optional<InstitutionalCustomer> findByAccreditationNumber(
            String accreditationNumber
    ) {

        if (accreditationNumber == null
                || accreditationNumber.isBlank()) {

            return Optional.empty();
        }

        return institutionalCustomerRepository
                .findByAccreditationNumber(
                        accreditationNumber.trim()
                );
    }

    @Transactional(readOnly = true)
    public Optional<InstitutionalCustomer> findByInstitutionCode(
            String institutionCode
    ) {

        if (institutionCode == null
                || institutionCode.isBlank()) {

            return Optional.empty();
        }

        return institutionalCustomerRepository
                .findByInstitutionCode(
                        institutionCode.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<InstitutionalCustomer> findByInstitutionType(
            InstitutionType institutionType
    ) {

        if (institutionType == null) {
            return List.of();
        }

        return institutionalCustomerRepository
                .findByInstitutionType(institutionType);
    }

    @Transactional(readOnly = true)
    public List<InstitutionalCustomer> findByAccreditingBody(
            String accreditingBody
    ) {

        if (accreditingBody == null
                || accreditingBody.isBlank()) {

            return List.of();
        }

        return institutionalCustomerRepository
                .findByAccreditingBodyContainingIgnoreCase(
                        accreditingBody.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<InstitutionalCustomer> findByGoverningBody(
            String governingBody
    ) {

        if (governingBody == null
                || governingBody.isBlank()) {

            return List.of();
        }

        return institutionalCustomerRepository
                .findByGoverningBodyContainingIgnoreCase(
                        governingBody.trim()
                );
    }

    @Transactional(readOnly = true)
    public List<InstitutionalCustomer> findByEmployeeCountGreaterThan(
            Integer employeeCount
    ) {

        if (employeeCount == null) {
            return List.of();
        }

        return institutionalCustomerRepository
                .findByEmployeeCountGreaterThan(employeeCount);
    }

    @Transactional(readOnly = true)
    public List<InstitutionalCustomer>
    findByStudentOrMemberCountGreaterThan(
            Integer count
    ) {

        if (count == null) {
            return List.of();
        }

        return institutionalCustomerRepository
                .findByStudentOrMemberCountGreaterThan(count);
    }

    // ========================================================================
    // UPDATE
    // ========================================================================

    public InstitutionalCustomer updateCustomer(
            InstitutionalCustomer customer
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

        if (!institutionalCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        validateUniqueFields(customer);

        return institutionalCustomerRepository.save(customer);
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

        if (!institutionalCustomerRepository.existsById(customerId)) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId
            );
        }

        institutionalCustomerRepository.deleteById(customerId);
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    @Transactional(readOnly = true)
    public boolean existsById(
            Long customerId
    ) {

        return customerId != null
                && institutionalCustomerRepository.existsById(customerId);
    }

    @Transactional(readOnly = true)
    public boolean existsByAccreditationNumber(
            String accreditationNumber
    ) {

        if (accreditationNumber == null
                || accreditationNumber.isBlank()) {

            return false;
        }

        return institutionalCustomerRepository
                .existsByAccreditationNumber(
                        accreditationNumber.trim()
                );
    }

    @Transactional(readOnly = true)
    public boolean existsByInstitutionCode(
            String institutionCode
    ) {

        if (institutionCode == null
                || institutionCode.isBlank()) {

            return false;
        }

        return institutionalCustomerRepository
                .existsByInstitutionCode(
                        institutionCode.trim()
                );
    }

    // ========================================================================
    // VALIDATION
    // ========================================================================

    private void validateUniqueFields(
            InstitutionalCustomer customer
    ) {

        Long customerId = customer.getCustomerId();

        String accreditationNumber =
                customer.getAccreditationNumber();

        if (accreditationNumber != null
                && !accreditationNumber.isBlank()) {

            Optional<InstitutionalCustomer> existingByAccreditation =
                    institutionalCustomerRepository
                            .findByAccreditationNumber(
                                    accreditationNumber.trim()
                            );

            if (existingByAccreditation.isPresent()
                    && !existingByAccreditation
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another institutional customer already exists "
                                + "with accreditation number: "
                                + accreditationNumber
                );
            }
        }

        String institutionCode =
                customer.getInstitutionCode();

        if (institutionCode != null
                && !institutionCode.isBlank()) {

            Optional<InstitutionalCustomer> existingByCode =
                    institutionalCustomerRepository
                            .findByInstitutionCode(
                                    institutionCode.trim()
                            );

            if (existingByCode.isPresent()
                    && !existingByCode
                            .get()
                            .getCustomerId()
                            .equals(customerId)) {

                throw new IllegalArgumentException(
                        "Another institutional customer already exists "
                                + "with institution code: "
                                + institutionCode
                );
            }
        }
    }
}
