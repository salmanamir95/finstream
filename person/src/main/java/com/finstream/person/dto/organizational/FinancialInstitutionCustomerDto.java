package com.finstream.person.dto.organizational;

import com.finstream.person.finStatus.FinancialInstitutionType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FinancialInstitutionCustomerDto extends OrganizationalCustomerDto {
    @NotNull(message = "Financial institution type is required")
    private FinancialInstitutionType financialInstitutionType;
    @NotBlank(message = "License number is required")
    @Size(max = 100, message = "License number must not exceed 100 characters")
    private String licenseNumber;
    @NotBlank(message = "Regulatory authority is required")
    @Size(max = 200, message = "Regulatory authority must not exceed 200 characters")
    private String regulatoryAuthority;
    @NotBlank(message = "License country is required")
    @Size(max = 100, message = "License country must not exceed 100 characters")
    private String licenseCountry;
    @Size(max = 20, message = "SWIFT code must not exceed 20 characters")
    private String swiftCode;
    @Size(max = 20, message = "BIC code must not exceed 20 characters")
    private String bicCode;
    @NotBlank(message = "Head office address is required")
    @Size(max = 500, message = "Head office address must not exceed 500 characters")
    private String headOfficeAddress;
    @NotNull(message = "Branch count is required")
    @Min(value = 0, message = "Branch count cannot be negative")
    private Integer branchCount;
    @NotNull(message = "Internationally operating status is required")
    private Boolean internationallyOperating;
}