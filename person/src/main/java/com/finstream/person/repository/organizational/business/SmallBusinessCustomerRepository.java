package com.finstream.person.repository.organizational.business;

import com.finstream.person.domain.customer.organizationalCustomer.businessCustomer.SmallBusinessCustomer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select customer from SmallBusinessCustomer customer "
            + "where lower(customer.primaryProductOrService) "
            + "like lower(concat('%', :primaryProductOrService, '%'))")
    List<SmallBusinessCustomer> findByPrimaryProductOrServiceContainingIgnoreCase(
            @Param("primaryProductOrService") String primaryProductOrService
    );

    boolean existsByOwnerNationalId(
            String ownerNationalId
    );

    boolean existsByBusinessLicenseNumber(
            String businessLicenseNumber
    );
}