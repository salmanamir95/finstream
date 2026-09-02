package com.finstream.person.domain.customer.organizational.business;

import java.math.BigDecimal;

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

    private Integer employeeCount;

    private BigDecimal annualRevenue;
}