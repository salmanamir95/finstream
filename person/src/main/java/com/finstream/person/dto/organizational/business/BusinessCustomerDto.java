package com.finstream.person.dto.organizational.business;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.finstream.person.business.BusinessSector;
import com.finstream.person.business.BusinessStructure;
import com.finstream.person.dto.organizational.OrganizationalCustomerDto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BusinessCustomerDto extends OrganizationalCustomerDto {
    @NotBlank(message = "Tax identification number is required")
    @Size(max = 100, message = "Tax identification number must not exceed 100 characters")
    private String taxIdentificationNumber;
    @NotNull(message = "Annual revenue is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Annual revenue cannot be negative")
    private BigDecimal annualRevenue;
    @NotBlank(message = "Industry is required")
    @Size(max = 150, message = "Industry must not exceed 150 characters")
    private String industry;
    @NotNull(message = "Business sector is required")
    private BusinessSector businessSector;
    @NotNull(message = "Business structure is required")
    private BusinessStructure businessStructure;
    @Size(max = 1000, message = "Business description must not exceed 1000 characters")
    private String businessDescription;
    @NotBlank(message = "Primary country of operation is required")
    @Size(max = 100, message = "Primary country of operation must not exceed 100 characters")
    private String primaryCountryOfOperation;
    @NotNull(message = "Operating since date is required")
    @Past(message = "Operating since date must be in the past")
    private LocalDate operatingSince;
}