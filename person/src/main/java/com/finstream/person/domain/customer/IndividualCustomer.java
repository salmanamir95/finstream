package com.finstream.person.domain.customer;

import java.time.LocalDate;

import com.finstream.person.gender.Gender;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
    
@Entity
@Table(name = "individual_customers")
@DiscriminatorValue("INDIVIDUAL")
@Getter
@Setter
public class IndividualCustomer extends Customer {

    private String firstName;

    private String middleName;

    private String lastName;

    private String nationalId;

    private LocalDate dateOfBirth;

    private String nationality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String occupation;

    private String employerName;
}