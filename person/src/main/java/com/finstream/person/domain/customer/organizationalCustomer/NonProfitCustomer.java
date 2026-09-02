package com.finstream.person.domain.customer.organizationalCustomer;

import com.finstream.person.nonprofit.NonProfitType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "non_profit_customers")
@DiscriminatorValue("NON_PROFIT")
@Getter
@Setter
public class NonProfitCustomer extends OrganizationalCustomer {

    @Enumerated(EnumType.STRING)
    private NonProfitType nonProfitType;

    private String taxExemptionNumber;

    private String registrationAuthority;

    private String missionStatement;
}