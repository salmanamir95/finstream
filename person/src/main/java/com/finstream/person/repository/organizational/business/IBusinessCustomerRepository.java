package com.finstream.person.repository.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.BusinessCustomer;
import com.finstream.person.business.BusinessSector;
import com.finstream.person.business.BusinessStructure;
import com.finstream.person.repository.organizational.IOrganizationalCustomerRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IBusinessCustomerRepository extends IOrganizationalCustomerRepository {

    Optional<BusinessCustomer> findByTaxIdentificationNumber(String taxIdentificationNumber);

    List<BusinessCustomer> findByIndustryContainingIgnoreCase(String industry);

    List<BusinessCustomer> findByBusinessSector(BusinessSector businessSector);

    List<BusinessCustomer> findByBusinessStructure(BusinessStructure businessStructure);

    List<BusinessCustomer> findByPrimaryCountryOfOperation(String primaryCountryOfOperation);

    List<BusinessCustomer> findByAnnualRevenueGreaterThan(BigDecimal annualRevenue);

    List<BusinessCustomer> findByAnnualRevenueBetween(BigDecimal min, BigDecimal max);

    List<BusinessCustomer> findByOperatingSinceBefore(LocalDate date);

    boolean existsByTaxIdentificationNumber(String taxIdentificationNumber);
}