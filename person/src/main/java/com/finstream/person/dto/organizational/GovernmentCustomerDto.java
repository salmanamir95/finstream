package com.finstream.person.dto.organizational;

import com.finstream.person.governmentstatus.GovernmentLevel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GovernmentCustomerDto extends OrganizationalCustomerDto {
    @NotBlank(message = "Government agency is required")
    @Size(max = 200, message = "Government agency must not exceed 200 characters")
    private String governmentAgency;
    @NotBlank(message = "Government ID is required")
    @Size(max = 100, message = "Government ID must not exceed 100 characters")
    private String governmentId;
    @NotNull(message = "Government level is required")
    private GovernmentLevel governmentLevel;
    @Size(max = 200, message = "Department name must not exceed 200 characters")
    private String departmentName;
    @NotBlank(message = "Jurisdiction is required")
    @Size(max = 200, message = "Jurisdiction must not exceed 200 characters")
    private String jurisdiction;
}