package com.finstream.person.repository.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.GovernmentCustomer;
import com.finstream.person.governmentstatus.GovernmentLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GovernmentCustomerRepository extends
        JpaRepository<GovernmentCustomer, Long>,
        IOrganizationalCustomerRepository {

    Optional<GovernmentCustomer> findByGovernmentId(String governmentId);

    List<GovernmentCustomer> findByGovernmentAgencyContainingIgnoreCase(String governmentAgency);

    List<GovernmentCustomer> findByGovernmentLevel(GovernmentLevel governmentLevel);

    List<GovernmentCustomer> findByDepartmentNameContainingIgnoreCase(String departmentName);

    List<GovernmentCustomer> findByJurisdiction(String jurisdiction);

    boolean existsByGovernmentId(String governmentId);
}