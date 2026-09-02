package com.finstream.person.domain.customer.organizationalCustomer.businessCustomer;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "medium_business_customers")
@DiscriminatorValue("MEDIUM_BUSINESS")
@Getter
@Setter
public class MediumBusinessCustomer extends BusinessCustomer {

    private Integer numberOfBranches;

    private Integer numberOfDepartments;

    private String chiefExecutiveName;

    private String financeManagerName;

    private Boolean auditedFinancials;
}