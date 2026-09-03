package com.finstream.person.repository.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.NonProfitCustomer;
import com.finstream.person.nonprofit.NonProfitType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NonProfitCustomerRepository extends
        JpaRepository<NonProfitCustomer, Long>,
        IOrganizationalCustomerRepository {

    Optional<NonProfitCustomer> findByTaxExemptionNumber(String taxExemptionNumber);

    List<NonProfitCustomer> findByNonProfitType(NonProfitType nonProfitType);

    List<NonProfitCustomer> findByRegistrationAuthorityContainingIgnoreCase(String registrationAuthority);

    List<NonProfitCustomer> findByMissionStatementContainingIgnoreCase(String missionStatement);

    boolean existsByTaxExemptionNumber(String taxExemptionNumber);
}