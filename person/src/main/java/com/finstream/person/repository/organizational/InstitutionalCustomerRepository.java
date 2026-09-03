package com.finstream.person.repository.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.InstitutionalCustomer;
import com.finstream.person.instituitions.InstitutionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstitutionalCustomerRepository extends
        JpaRepository<InstitutionalCustomer, Long>,
        IOrganizationalCustomerRepository {

    Optional<InstitutionalCustomer> findByAccreditationNumber(String accreditationNumber);

    Optional<InstitutionalCustomer> findByInstitutionCode(String institutionCode);

    List<InstitutionalCustomer> findByInstitutionType(InstitutionType institutionType);

    List<InstitutionalCustomer> findByAccreditingBodyContainingIgnoreCase(String accreditingBody);

    List<InstitutionalCustomer> findByGoverningBodyContainingIgnoreCase(String governingBody);

    List<InstitutionalCustomer> findByEmployeeCountGreaterThan(Integer employeeCount);

    List<InstitutionalCustomer> findByStudentOrMemberCountGreaterThan(Integer count);

    boolean existsByAccreditationNumber(String accreditationNumber);

    boolean existsByInstitutionCode(String institutionCode);
}