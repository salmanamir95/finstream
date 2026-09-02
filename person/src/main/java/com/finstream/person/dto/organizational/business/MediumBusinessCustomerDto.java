package com.finstream.person.dto.organizational.business;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MediumBusinessCustomerDto extends BusinessCustomerDto {
    @NotNull(message = "Number of branches is required")
    @Min(value = 0, message = "Number of branches cannot be negative")
    private Integer numberOfBranches;
    @NotNull(message = "Number of departments is required")
    @Min(value = 0, message = "Number of departments cannot be negative")
    private Integer numberOfDepartments;
    @NotBlank(message = "Chief executive name is required")
    @Size(max = 150, message = "Chief executive name must not exceed 150 characters")
    private String chiefExecutiveName;
    @NotBlank(message = "Finance manager name is required")
    @Size(max = 150, message = "Finance manager name must not exceed 150 characters")
    private String financeManagerName;
    @NotNull(message = "Audited financials status is required")
    private Boolean auditedFinancials;
}