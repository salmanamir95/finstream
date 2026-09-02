package com.finstream.person.domain.customer.organizationalCustomer.businessCustomer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "large_business_customers")
@DiscriminatorValue("LARGE_BUSINESS")
@Getter
@Setter
public class LargeBusinessCustomer extends BusinessCustomer {

    private Integer employeeCount;

    private Long annualRevenue;

}