package com.finstream.person.dto;

import java.time.LocalDate;

import com.finstream.person.gender.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndividualCustomerDto extends CustomerDto {

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @NotBlank(message = "National ID is required")
    @Size(max = 50, message = "National ID must not exceed 50 characters")
    private String nationalId;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Nationality is required")
    @Size(max = 100, message = "Nationality must not exceed 100 characters")
    private String nationality;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Size(max = 150, message = "Occupation must not exceed 150 characters")
    private String occupation;

    @Size(max = 150, message = "Employer name must not exceed 150 characters")
    private String employerName;
}