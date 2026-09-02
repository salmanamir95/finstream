package com.finstream.person.domain.customer.organizationalCustomer;

import com.finstream.person.domain.customer.Customer;
import com.finstream.person.governmentstatus.GovernmentLevel;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "government_customers")
@DiscriminatorValue("GOVERNMENT")
@Getter
@Setter
public class GovernmentCustomer extends OrganizationalCustomer {

    private String governmentAgency;

    private String governmentId;

    @Enumerated(EnumType.STRING)
    private GovernmentLevel governmentLevel;

    private String departmentName;

    private String jurisdiction;
}