package com.finstream.person.domain.customer.organizationalCustomer;

import java.time.LocalDate;

import com.finstream.person.domain.customer.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organizational_customers")
@Getter
@Setter
public abstract class OrganizationalCustomer extends Customer {

    private String legalName;

    private String tradingName;

    private String registrationNumber;

    private String registrationCountry;

    private LocalDate incorporationDate;

    private String organizationWebsite;

    private String primaryContactName;

    private String primaryContactPhone;

    private String primaryContactEmail;
}