
package com.finstream.person.dto.organizational;

import java.time.LocalDate;

import com.finstream.person.dto.CustomerDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class OrganizationalCustomerDto extends CustomerDto {

    @NotBlank(message = "Legal name is required")
    @Size(max = 200, message = "Legal name must not exceed 200 characters")
    private String legalName;

    @Size(max = 200, message = "Trading name must not exceed 200 characters")
    private String tradingName;

    @NotBlank(message = "Registration number is required")
    @Size(max = 100, message = "Registration number must not exceed 100 characters")
    private String registrationNumber;

    @NotBlank(message = "Registration country is required")
    @Size(max = 100, message = "Registration country must not exceed 100 characters")
    private String registrationCountry;

    @NotNull(message = "Incorporation date is required")
    @Past(message = "Incorporation date must be in the past")
    private LocalDate incorporationDate;

    @Size(max = 255, message = "Organization website must not exceed 255 characters")
    private String organizationWebsite;

    @NotBlank(message = "Primary contact name is required")
    @Size(max = 150, message = "Primary contact name must not exceed 150 characters")
    private String primaryContactName;

    @NotBlank(message = "Primary contact phone is required")
    @Size(max = 30, message = "Primary contact phone must not exceed 30 characters")
    private String primaryContactPhone;

    @NotBlank(message = "Primary contact email is required")
    @Email(message = "Primary contact email must be valid")
    @Size(max = 255, message = "Primary contact email must not exceed 255 characters")
    private String primaryContactEmail;
}
