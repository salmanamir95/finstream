package com.finstream.person.domain.customer.organizationalCustomer;


import com.finstream.person.instituitions.InstitutionType;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "institutional_customers")
@DiscriminatorValue("INSTITUTIONAL")
@Getter
@Setter
public class InstitutionalCustomer extends OrganizationalCustomer {

    @Enumerated(EnumType.STRING)
    private InstitutionType institutionType;

    private String accreditationNumber;

    private String accreditingBody;

    private String institutionCode;

    private String governingBody;

    private Integer employeeCount;

    private Integer studentOrMemberCount;
}