package com.finstream.person.repository.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.LargeBusinessCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LargeBusinessCustomerRepository extends
        JpaRepository<LargeBusinessCustomer, Long>,
        IBusinessCustomerRepository {

    List<LargeBusinessCustomer> findByNumberOfBranchesGreaterThan(
            Integer numberOfBranches
    );

    List<LargeBusinessCustomer> findByNumberOfSubsidiariesGreaterThan(
            Integer numberOfSubsidiaries
    );

    List<LargeBusinessCustomer> findByChiefExecutiveNameContainingIgnoreCase(
            String chiefExecutiveName
    );

    List<LargeBusinessCustomer> findByChiefFinancialOfficerContainingIgnoreCase(
            String chiefFinancialOfficer
    );

    List<LargeBusinessCustomer> findByCorporateGroupNameContainingIgnoreCase(
            String corporateGroupName
    );

    List<LargeBusinessCustomer> findByPubliclyListedTrue();

    List<LargeBusinessCustomer> findByPubliclyListedFalse();

    List<LargeBusinessCustomer> findByStockExchange(
            String stockExchange
    );

    List<LargeBusinessCustomer> findByAuditedFinancialsTrue();

    List<LargeBusinessCustomer> findByAuditedFinancialsFalse();
}