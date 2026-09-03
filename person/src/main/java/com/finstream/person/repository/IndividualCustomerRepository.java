package com.finstream.person.repository;

import com.finstream.person.domain.customer.IndividualCustomer;
import com.finstream.person.gender.Gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IndividualCustomerRepository
        extends JpaRepository<IndividualCustomer, Long>,
                ICustomerRepository {

    Optional<IndividualCustomer> findByNationalId(String nationalId);

    boolean existsByNationalId(String nationalId);

    List<IndividualCustomer> findByFirstNameContainingIgnoreCase(String firstName);

    List<IndividualCustomer> findByLastNameContainingIgnoreCase(String lastName);

    List<IndividualCustomer> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );

    List<IndividualCustomer> findByNationality(String nationality);

    List<IndividualCustomer> findByGender(Gender gender);

    List<IndividualCustomer> findByOccupation(String occupation);

    List<IndividualCustomer> findByEmployerName(String employerName);

    List<IndividualCustomer> findByDateOfBirth(LocalDate dateOfBirth);

    List<IndividualCustomer> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);
}