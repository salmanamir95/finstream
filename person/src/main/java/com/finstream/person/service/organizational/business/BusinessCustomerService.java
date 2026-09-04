package com.finstream.person.service.organizational.business;

import com.finstream.person.business.BusinessSector;
import com.finstream.person.business.BusinessStructure;
import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.BusinessCustomer;
import com.finstream.person.repository.organizational.business.IBusinessCustomerRepository;
import com.finstream.person.service.organizational.OrganizationalCustomerService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public class BusinessCustomerService
        extends OrganizationalCustomerService {

    protected final IBusinessCustomerRepository
            businessCustomerRepository;

    public BusinessCustomerService(
            IBusinessCustomerRepository businessCustomerRepository
    ) {
        super(businessCustomerRepository);

        this.businessCustomerRepository =
                businessCustomerRepository;
    }

    // ========================================================================
    // BUSINESS CUSTOMER - READ
    // ========================================================================

    public Optional<BusinessCustomer> findByTaxIdentificationNumber(
            String taxIdentificationNumber
    ) {

        if (taxIdentificationNumber == null
                || taxIdentificationNumber.isBlank()) {

            return Optional.empty();
        }

        return businessCustomerRepository
                .findByTaxIdentificationNumber(
                        taxIdentificationNumber.trim()
                );
    }

    public List<BusinessCustomer> findByIndustry(
            String industry
    ) {

        if (industry == null || industry.isBlank()) {
            return List.of();
        }

        return businessCustomerRepository
                .findByIndustryContainingIgnoreCase(
                        industry.trim()
                );
    }

    public List<BusinessCustomer> findByBusinessSector(
            BusinessSector businessSector
    ) {

        if (businessSector == null) {
            return List.of();
        }

        return businessCustomerRepository
                .findByBusinessSector(
                        businessSector
                );
    }

    public List<BusinessCustomer> findByBusinessStructure(
            BusinessStructure businessStructure
    ) {

        if (businessStructure == null) {
            return List.of();
        }

        return businessCustomerRepository
                .findByBusinessStructure(
                        businessStructure
                );
    }

    public List<BusinessCustomer> findByPrimaryCountryOfOperation(
            String primaryCountryOfOperation
    ) {

        if (primaryCountryOfOperation == null
                || primaryCountryOfOperation.isBlank()) {

            return List.of();
        }

        return businessCustomerRepository
                .findByPrimaryCountryOfOperation(
                        primaryCountryOfOperation.trim()
                );
    }

    public List<BusinessCustomer> findByAnnualRevenueGreaterThan(
            BigDecimal annualRevenue
    ) {

        if (annualRevenue == null) {
            return List.of();
        }

        return businessCustomerRepository
                .findByAnnualRevenueGreaterThan(
                        annualRevenue
                );
    }

    public List<BusinessCustomer> findByAnnualRevenueBetween(
            BigDecimal min,
            BigDecimal max
    ) {

        if (min == null || max == null) {
            return List.of();
        }

        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException(
                    "Minimum annual revenue must not be greater than maximum annual revenue"
            );
        }

        return businessCustomerRepository
                .findByAnnualRevenueBetween(
                        min,
                        max
                );
    }

    public List<BusinessCustomer> findByOperatingSinceBefore(
            LocalDate date
    ) {

        if (date == null) {
            return List.of();
        }

        return businessCustomerRepository
                .findByOperatingSinceBefore(
                        date
                );
    }

    // ========================================================================
    // EXISTENCE
    // ========================================================================

    public boolean existsByTaxIdentificationNumber(
            String taxIdentificationNumber
    ) {

        if (taxIdentificationNumber == null
                || taxIdentificationNumber.isBlank()) {

            return false;
        }

        return businessCustomerRepository
                .existsByTaxIdentificationNumber(
                        taxIdentificationNumber.trim()
                );
    }
}
