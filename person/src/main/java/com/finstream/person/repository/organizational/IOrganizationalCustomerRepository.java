package com.finstream.person.repository.organizational;

import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;
import com.finstream.person.repository.ICustomerRepository;

import org.springframework.data.repository.NoRepositoryBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface IOrganizationalCustomerRepository extends ICustomerRepository {

    Optional<OrganizationalCustomer> findByRegistrationNumber(String registrationNumber);

    List<OrganizationalCustomer> findByLegalNameContainingIgnoreCase(String legalName);

    List<OrganizationalCustomer> findByTradingNameContainingIgnoreCase(String tradingName);

    List<OrganizationalCustomer> findByRegistrationCountry(String registrationCountry);

    List<OrganizationalCustomer> findByIncorporationDateBetween(LocalDate start, LocalDate end);

    List<OrganizationalCustomer> findByPrimaryContactEmail(String primaryContactEmail);

    boolean existsByRegistrationNumber(String registrationNumber);
}