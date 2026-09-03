package com.finstream.person.repository.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.SmallBusinessCustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SmallBusinessCustomerRepository extends
        JpaRepository<SmallBusinessCustomer, Long>,
        IBusinessCustomerRepository {

    Optional<SmallBusinessCustomer> findByOwnerNationalId(
            String ownerNationalId
    );

    List<SmallBusinessCustomer> findByOwnerNameContainingIgnoreCase(
            String ownerName
    );

    List<SmallBusinessCustomer> findByOwnerManagedTrue();

    List<SmallBusinessCustomer> findByOwnerManagedFalse();

    Optional<SmallBusinessCustomer> findByBusinessLicenseNumber(
            String businessLicenseNumber
    );

    List<SmallBusinessCustomer> findByPrimaryProductOrServiceContainingIgnoreCase(
            String primaryProductOrService
    );

    boolean existsByOwnerNationalId(
            String ownerNationalId
    );

    boolean existsByBusinessLicenseNumber(
            String businessLicenseNumber
    );
}