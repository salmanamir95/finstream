package com.finstream.person.dto.organizational.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmallBusinessCustomerDto extends BusinessCustomerDto {
    @NotBlank(message = "Owner name is required")
    @Size(max = 150, message = "Owner name must not exceed 150 characters")
    private String ownerName;
    @NotBlank(message = "Owner national ID is required")
    @Size(max = 50, message = "Owner national ID must not exceed 50 characters")
    private String ownerNationalId;
    @NotNull(message = "Owner managed status is required")
    private Boolean ownerManaged;
    @NotBlank(message = "Business license number is required")
    @Size(max = 100, message = "Business license number must not exceed 100 characters")
    private String businessLicenseNumber;
    @NotBlank(message = "Primary product or service is required")
    @Size(max = 300, message = "Primary product or service must not exceed 300 characters")
    private String primaryProductOrService;
}