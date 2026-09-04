package com.finstream.person.service.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;
import com.finstream.person.repository.organizational.IOrganizationalCustomerRepository;
import com.finstream.person.service.CustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrganizationalCustomerService
        extends CustomerService {

    protected final IOrganizationalCustomerRepository
            organizationalCustomerRepository;

    public OrganizationalCustomerService(
            IOrganizationalCustomerRepository organizationalCustomerRepository
    ) {
        super(organizationalCustomerRepository);

        this.organizationalCustomerRepository =
                organizationalCustomerRepository;
    }

    // ========================================================================
    // ORGANIZATIONAL CUSTOMER - READ
    // ========================================================================

    public Optional<OrganizationalCustomer> findByRegistrationNumber(
            String registrationNumber
    ) {

        if (registrationNumber == null
                || registrationNumber.isBlank()) {

            return Optional.empty();
        }

        return organizationalCustomerRepository
                .findByRegistrationNumber(
                        registrationNumber.trim()
                );
    }

    public List<OrganizationalCustomer> findByLegalName(
            String legalName
    ) {

        if (legalName == null || legalName.isBlank()) {
            return List.of();
        }

        return organizationalCustomerRepository
                .findByLegalNameContainingIgnoreCase(
                        legalName.trim()
                );
    }

    public List<OrganizationalCustomer> findByTradingName(
            String tradingName
    ) {

        if (tradingName == null || tradingName.isBlank()) {
            return List.of();
        }

        return organizationalCustomerRepository
                .findByTradingNameContainingIgnoreCase(
                        tradingName.trim()
                );
    }

    public List<OrganizationalCustomer> findByRegistrationCountry(
            String registrationCountry
    ) {

        if (registrationCountry == null
                || registrationCountry.isBlank()) {

            return List.of();
        }

        return organizationalCustomerRepository
                .findByRegistrationCountry(
                        registrationCountry.trim()
                );
    }

    public List<OrganizationalCustomer> findByIncorporationDateBetween(
            LocalDate start,
            LocalDate end
    ) {

        if (start == null || end == null) {
            return List.of();
        }

        if (start.isAfter(end)) {
            throw new IllegalArgumentException(
                    "Start date must not be after end date"
            );
        }

        return organizationalCustomerRepository
                .findByIncorporationDateBetween(
                        start,
                        end
                );
    }

    public List<OrganizationalCustomer> findByPrimaryContactEmail(
            String primaryContactEmail
    ) {

        if (primaryContactEmail == null
                || primaryContactEmail.isBlank()) {

            return List.of();
        }

        return organizationalCustomerRepository
                .findByPrimaryContactEmail(
                        primaryContactEmail.trim()
                );
    }

    // ========================================================================
    // ORGANIZATIONAL CUSTOMER - EXISTENCE
    // ========================================================================

    public boolean existsByRegistrationNumber(
            String registrationNumber
    ) {

        if (registrationNumber == null
                || registrationNumber.isBlank()) {

            return false;
        }

        return organizationalCustomerRepository
                .existsByRegistrationNumber(
                        registrationNumber.trim()
                );
    }
}
