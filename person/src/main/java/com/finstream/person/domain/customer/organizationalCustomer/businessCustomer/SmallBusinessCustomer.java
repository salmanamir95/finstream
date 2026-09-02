package com.finstream.person.domain.customer.organizationalCustomer.businessCustomer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "small_business_customers")
@DiscriminatorValue("SMALL_BUSINESS")
@Getter
@Setter
public class SmallBusinessCustomer extends BusinessCustomer {

    private String ownerName;

    private String ownerNationalId;

    private Boolean ownerManaged;

    private String businessLicenseNumber;

    private String primaryProductOrService;

}

