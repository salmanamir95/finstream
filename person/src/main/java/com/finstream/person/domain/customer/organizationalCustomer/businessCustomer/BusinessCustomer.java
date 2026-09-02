package com.finstream.person.domain.customer.organizationalCustomer.businessCustomer;

import java.math.BigDecimal;

import com.finstream.person.domain.customer.organizationalCustomer.OrganizationalCustomer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "business_customers")
@DiscriminatorValue("BUSINESS")
@Getter
@Setter
public abstract class BusinessCustomer extends OrganizationalCustomer {

    private String businessRegistrationNumber;

    private String taxIdentificationNumber;

    private Integer employeeCount;

    private BigDecimal annualRevenue;

    private String industry;

    private String businessSector;

    private String website;

    private String businessDescription;

    private String headquartersAddress;

    private String countryOfOperation;

}