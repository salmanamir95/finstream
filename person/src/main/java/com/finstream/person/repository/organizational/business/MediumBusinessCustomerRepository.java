package com.finstream.person.repository.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.MediumBusinessCustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediumBusinessCustomerRepository extends
        JpaRepository<MediumBusinessCustomer, Long>,
        IBusinessCustomerRepository {

    List<MediumBusinessCustomer> findByNumberOfBranchesGreaterThan(
            Integer numberOfBranches
    );

    List<MediumBusinessCustomer> findByNumberOfDepartmentsGreaterThan(
            Integer numberOfDepartments
    );

    List<MediumBusinessCustomer> findByChiefExecutiveNameContainingIgnoreCase(
            String chiefExecutiveName
    );

    List<MediumBusinessCustomer> findByFinanceManagerNameContainingIgnoreCase(
            String financeManagerName
    );

    List<MediumBusinessCustomer> findByAuditedFinancialsTrue();

    List<MediumBusinessCustomer> findByAuditedFinancialsFalse();
}