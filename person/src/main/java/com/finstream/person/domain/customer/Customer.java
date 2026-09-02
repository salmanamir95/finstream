package com.finstream.person.domain.customer;

import com.finstream.person.regChannel.RegistrationChannel;
import com.finstream.person.status.CustomerStatus;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
    name = "customer_type",
    discriminatorType = DiscriminatorType.STRING
)
@Getter
@Setter
public abstract class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String displayName;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @Enumerated(EnumType.STRING)
    private RegistrationChannel registrationChannel;

    private String registrationBranchCode;

    private String email;

    private String phoneNumber;

    private String country;

    private String city;

    private String address;
}   