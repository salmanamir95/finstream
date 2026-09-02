package com.finstream.person.dto.organizational.business;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LargeBusinessCustomerDto extends BusinessCustomerDto {
    @NotNull(message = "Number of branches is required")
    @Min(value = 0, message = "Number of branches cannot be negative")
    private Integer numberOfBranches;
    @NotNull(message = "Number of subsidiaries is required")
    @Min(value = 0, message = "Number of subsidiaries cannot be negative")
    private Integer numberOfSubsidiaries;
    @NotBlank(message = "Chief executive name is required")
    @Size(max = 150, message = "Chief executive name must not exceed 150 characters")
    private String chiefExecutiveName;
    @NotBlank(message = "Chief financial officer is required")
    @Size(max = 150, message = "Chief financial officer must not exceed 150 characters")
    private String chiefFinancialOfficer;
    @Size(max = 200, message = "Corporate group name must not exceed 200 characters")
    private String corporateGroupName;
    @NotNull(message = "Publicly listed status is required")
    private Boolean publiclyListed;
    @Size(max = 100, message = "Stock exchange must not exceed 100 characters")
    private String stockExchange;
    @NotNull(message = "Audited financials status is required")
    private Boolean auditedFinancials;
}