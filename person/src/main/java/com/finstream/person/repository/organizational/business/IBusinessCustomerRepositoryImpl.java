package com.finstream.person.repository.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.BusinessCustomer;
import com.finstream.person.business.BusinessSector;
import com.finstream.person.business.BusinessStructure;
import com.finstream.person.repository.organizational.IOrganizationalCustomerRepositoryImpl;

import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class IBusinessCustomerRepositoryImpl
        extends IOrganizationalCustomerRepositoryImpl
        implements IBusinessCustomerRepository {

    // findByEmail, findByStatus, findByCountry... (from ICustomerRepositoryImpl)
    // findByRegistrationNumber, findByLegalName... (from IOrganizationalCustomerRepositoryImpl)
    // are ALL inherited here — zero duplication across three tiers.

    @Override
    public Optional<BusinessCustomer> findByTaxIdentificationNumber(String taxIdentificationNumber) {
        if (taxIdentificationNumber == null || taxIdentificationNumber.isBlank()) {
            return Optional.empty();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.taxIdentificationNumber = :tin",
                BusinessCustomer.class
        );
        query.setParameter("tin", taxIdentificationNumber.trim());
        query.setMaxResults(1);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<BusinessCustomer> findByIndustryContainingIgnoreCase(String industry) {
        if (industry == null || industry.isBlank()) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE LOWER(b.industry) LIKE LOWER(:pattern)",
                BusinessCustomer.class
        );
        query.setParameter("pattern", "%" + industry.trim() + "%");
        return query.getResultList();
    }

    @Override
    public List<BusinessCustomer> findByBusinessSector(BusinessSector businessSector) {
        if (businessSector == null) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.businessSector = :sector",
                BusinessCustomer.class
        );
        query.setParameter("sector", businessSector);
        return query.getResultList();
    }

    @Override
    public List<BusinessCustomer> findByBusinessStructure(BusinessStructure businessStructure) {
        if (businessStructure == null) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.businessStructure = :structure",
                BusinessCustomer.class
        );
        query.setParameter("structure", businessStructure);
        return query.getResultList();
    }

    @Override
    public List<BusinessCustomer> findByPrimaryCountryOfOperation(String primaryCountryOfOperation) {
        if (primaryCountryOfOperation == null || primaryCountryOfOperation.isBlank()) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.primaryCountryOfOperation = :country",
                BusinessCustomer.class
        );
        query.setParameter("country", primaryCountryOfOperation.trim());
        return query.getResultList();
    }

    @Override
    public List<BusinessCustomer> findByAnnualRevenueGreaterThan(BigDecimal annualRevenue) {
        if (annualRevenue == null) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.annualRevenue > :revenue",
                BusinessCustomer.class
        );
        query.setParameter("revenue", annualRevenue);
        return query.getResultList();
    }

    @Override
    public List<BusinessCustomer> findByAnnualRevenueBetween(BigDecimal min, BigDecimal max) {
        if (min == null || max == null) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.annualRevenue BETWEEN :min AND :max",
                BusinessCustomer.class
        );
        query.setParameter("min", min);
        query.setParameter("max", max);
        return query.getResultList();
    }

    @Override
    public List<BusinessCustomer> findByOperatingSinceBefore(LocalDate date) {
        if (date == null) {
            return Collections.emptyList();
        }
        TypedQuery<BusinessCustomer> query = entityManager.createQuery(
                "SELECT b FROM BusinessCustomer b WHERE b.operatingSince < :date",
                BusinessCustomer.class
        );
        query.setParameter("date", date);
        return query.getResultList();
    }

    @Override
    public boolean existsByTaxIdentificationNumber(String taxIdentificationNumber) {
        if (taxIdentificationNumber == null || taxIdentificationNumber.isBlank()) {
            return false;
        }
        Long count = entityManager.createQuery(
                "SELECT COUNT(b) FROM BusinessCustomer b WHERE b.taxIdentificationNumber = :tin",
                Long.class
        ).setParameter("tin", taxIdentificationNumber.trim()).getSingleResult();
        return count != null && count > 0;
    }
}