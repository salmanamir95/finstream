package com.finstream.person.repository.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;
import com.finstream.person.repository.ICustomerRepositoryImpl;

import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class IOrganizationalCustomerRepositoryImpl
        extends ICustomerRepositoryImpl
        implements IOrganizationalCustomerRepository {

    // findByEmail, findByStatus, findByCountry, etc. are ALL inherited
    // for free from ICustomerRepositoryImpl — zero duplication.
    // entityManager is also inherited (declared protected there, see note below).

    @Override
    public Optional<OrganizationalCustomer> findByRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            return Optional.empty();
        }
        TypedQuery<OrganizationalCustomer> query = entityManager.createQuery(
                "SELECT o FROM OrganizationalCustomer o WHERE o.registrationNumber = :regNum",
                OrganizationalCustomer.class
        );
        query.setParameter("regNum", registrationNumber.trim());
        query.setMaxResults(1);
        return query.getResultStream().findFirst();
    }

    @Override
    public List<OrganizationalCustomer> findByLegalNameContainingIgnoreCase(String legalName) {
        if (legalName == null || legalName.isBlank()) {
            return Collections.emptyList();
        }
        TypedQuery<OrganizationalCustomer> query = entityManager.createQuery(
                "SELECT o FROM OrganizationalCustomer o WHERE LOWER(o.legalName) LIKE LOWER(:pattern)",
                OrganizationalCustomer.class
        );
        query.setParameter("pattern", "%" + legalName.trim() + "%");
        return query.getResultList();
    }

    @Override
    public List<OrganizationalCustomer> findByTradingNameContainingIgnoreCase(String tradingName) {
        if (tradingName == null || tradingName.isBlank()) {
            return Collections.emptyList();
        }
        TypedQuery<OrganizationalCustomer> query = entityManager.createQuery(
                "SELECT o FROM OrganizationalCustomer o WHERE LOWER(o.tradingName) LIKE LOWER(:pattern)",
                OrganizationalCustomer.class
        );
        query.setParameter("pattern", "%" + tradingName.trim() + "%");
        return query.getResultList();
    }

    @Override
    public List<OrganizationalCustomer> findByRegistrationCountry(String registrationCountry) {
        if (registrationCountry == null || registrationCountry.isBlank()) {
            return Collections.emptyList();
        }
        TypedQuery<OrganizationalCustomer> query = entityManager.createQuery(
                "SELECT o FROM OrganizationalCustomer o WHERE o.registrationCountry = :country",
                OrganizationalCustomer.class
        );
        query.setParameter("country", registrationCountry.trim());
        return query.getResultList();
    }

    @Override
    public List<OrganizationalCustomer> findByIncorporationDateBetween(LocalDate start, LocalDate end) {
        if (start == null || end == null) {
            return Collections.emptyList();
        }
        TypedQuery<OrganizationalCustomer> query = entityManager.createQuery(
                "SELECT o FROM OrganizationalCustomer o WHERE o.incorporationDate BETWEEN :start AND :end",
                OrganizationalCustomer.class
        );
        query.setParameter("start", start);
        query.setParameter("end", end);
        return query.getResultList();
    }

    @Override
    public List<OrganizationalCustomer> findByPrimaryContactEmail(String primaryContactEmail) {
        if (primaryContactEmail == null || primaryContactEmail.isBlank()) {
            return Collections.emptyList();
        }
        TypedQuery<OrganizationalCustomer> query = entityManager.createQuery(
                "SELECT o FROM OrganizationalCustomer o WHERE LOWER(o.primaryContactEmail) = LOWER(:email)",
                OrganizationalCustomer.class
        );
        query.setParameter("email", primaryContactEmail.trim());
        return query.getResultList();
    }

    @Override
    public boolean existsByRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.isBlank()) {
            return false;
        }
        Long count = entityManager.createQuery(
                "SELECT COUNT(o) FROM OrganizationalCustomer o WHERE o.registrationNumber = :regNum",
                Long.class
        ).setParameter("regNum", registrationNumber.trim()).getSingleResult();
        return count != null && count > 0;
    }
}