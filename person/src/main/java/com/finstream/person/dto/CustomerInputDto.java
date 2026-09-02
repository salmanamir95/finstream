package com.finstream.person.dto;


import com.finstream.person.regChannel.RegistrationChannel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CustomerInputDto {
    @NotBlank(message = "Display name is required")
    @Size(max = 150, message = "Display name must not exceed 150 characters")
    private String displayName;
    @NotNull(message = "Registration channel is required")
    private RegistrationChannel registrationChannel;
    @Size(max = 20, message = "Registration branch code must not exceed 20 characters")
    private String registrationBranchCode;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 255, message = "Email must not exceed 255 characters")
    private String email;
    @NotBlank(message = "Phone number is required")
    @Size(max = 30, message = "Phone number must not exceed 30 characters")
    private String phoneNumber;
    @NotBlank(message = "Country is required")
    @Size(max = 100, message = "Country must not exceed 100 characters")
    private String country;
    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City must not exceed 100 characters")
    private String city;
    @NotBlank(message = "Address is required")
    @Size(max = 500, message = "Address must not exceed 500 characters")
    private String address;
}