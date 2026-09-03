package com.finstream.person.repository.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.FinancialInstitutionCustomer;
import com.finstream.person.finStatus.FinancialInstitutionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FinancialInstitutionCustomerRepository extends
        JpaRepository<FinancialInstitutionCustomer, Long>,
        IOrganizationalCustomerRepository {

    Optional<FinancialInstitutionCustomer> findByLicenseNumber(String licenseNumber);

    Optional<FinancialInstitutionCustomer> findBySwiftCode(String swiftCode);

    Optional<FinancialInstitutionCustomer> findByBicCode(String bicCode);

    List<FinancialInstitutionCustomer> findByFinancialInstitutionType(FinancialInstitutionType type);

    List<FinancialInstitutionCustomer> findByRegulatoryAuthority(String regulatoryAuthority);

    List<FinancialInstitutionCustomer> findByLicenseCountry(String licenseCountry);

    List<FinancialInstitutionCustomer> findByInternationallyOperatingTrue();

    List<FinancialInstitutionCustomer> findByBranchCountGreaterThan(Integer branchCount);

    boolean existsByLicenseNumber(String licenseNumber);

    boolean existsBySwiftCode(String swiftCode);
}