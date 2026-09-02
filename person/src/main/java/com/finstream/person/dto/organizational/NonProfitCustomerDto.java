package com.finstream.person.dto.organizational;

import com.finstream.person.nonprofit.NonProfitType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonProfitCustomerDto extends OrganizationalCustomerDto {
    @NotNull(message = "Non-profit type is required")
    private NonProfitType nonProfitType;
    @NotBlank(message = "Tax exemption number is required")
    @Size(max = 100, message = "Tax exemption number must not exceed 100 characters")
    private String taxExemptionNumber;
    @NotBlank(message = "Registration authority is required")
    @Size(max = 200, message = "Registration authority must not exceed 200 characters")
    private String registrationAuthority;
    @NotBlank(message = "Mission statement is required")
    @Size(max = 1000, message = "Mission statement must not exceed 1000 characters")
    private String missionStatement;
}