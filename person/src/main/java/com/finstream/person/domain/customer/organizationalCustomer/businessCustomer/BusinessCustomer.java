package com.finstream.person.domain.customer.organizationalCustomer.businessCustomer;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.finstream.person.business.BusinessSector;
import com.finstream.person.business.BusinessStructure;
import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business_customers")
@DiscriminatorValue("BUSINESS")
@Getter
@Setter
public abstract class BusinessCustomer extends OrganizationalCustomer {

    private String taxIdentificationNumber;

    private BigDecimal annualRevenue;

    private String industry;

    @Enumerated(EnumType.STRING)
    private BusinessSector businessSector;

    @Enumerated(EnumType.STRING)
    private BusinessStructure businessStructure;

    private String businessDescription;

    private String primaryCountryOfOperation;

    private LocalDate operatingSince;
}