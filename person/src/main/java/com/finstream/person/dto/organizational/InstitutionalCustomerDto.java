package com.finstream.person.dto.organizational;

import com.finstream.person.instituitions.InstitutionType;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstitutionalCustomerDto extends OrganizationalCustomerDto {
    @NotNull(message = "Institution type is required")
    private InstitutionType institutionType;
    @NotBlank(message = "Accreditation number is required")
    @Size(max = 100, message = "Accreditation number must not exceed 100 characters")
    private String accreditationNumber;
    @NotBlank(message = "Accrediting body is required")
    @Size(max = 200, message = "Accrediting body must not exceed 200 characters")
    private String accreditingBody;
    @NotBlank(message = "Institution code is required")
    @Size(max = 100, message = "Institution code must not exceed 100 characters")
    private String institutionCode;
    @NotBlank(message = "Governing body is required")
    @Size(max = 200, message = "Governing body must not exceed 200 characters")
    private String governingBody;
    @NotNull(message = "Employee count is required")
    @Min(value = 0, message = "Employee count cannot be negative")
    private Integer employeeCount;
    @NotNull(message = "Student or member count is required")
    @Min(value = 0, message = "Student or member count cannot be negative")
    private Integer studentOrMemberCount;
}