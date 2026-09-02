package com.finstream.person.domain.customer.organizationalCustomer;

import com.finstream.person.finStatus.FinancialInstitutionType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "financial_institution_customers")
@DiscriminatorValue("FINANCIAL_INSTITUTION")
@Getter
@Setter
public class FinancialInstitutionCustomer extends OrganizationalCustomer {

    @Enumerated(EnumType.STRING)
    private FinancialInstitutionType financialInstitutionType;

    private String licenseNumber;

    private String regulatoryAuthority;

    private String licenseCountry;

    private String swiftCode;

    private String bicCode;

    private String headOfficeAddress;

    private Integer branchCount;

    private Boolean internationallyOperating;
}