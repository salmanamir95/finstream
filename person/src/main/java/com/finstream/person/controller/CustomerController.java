package com.finstream.person.controller;

import com.finstream.common.response.GenericResponse;
import com.finstream.person.domain.customer.Customer;
import com.finstream.person.regChannel.RegistrationChannel;
import com.finstream.person.service.CustomerService;
import com.finstream.person.status.CustomerStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class CustomerController<D> {

    protected final CustomerService customerService;

    protected CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    protected abstract D toDto(Customer customer);

    @GetMapping("/search/email")
    public GenericResponse<D> findByEmail(@RequestParam String email) {
        return customerService.findByEmail(email)
                .map(customer -> GenericResponse.success(
                        toDto(customer), "Customer retrieved successfully"))
                .orElseGet(() -> GenericResponse.failure("Customer not found"));
    }

    @GetMapping("/search/phone")
    public GenericResponse<D> findByPhoneNumber(@RequestParam String phoneNumber) {
        return customerService.findByPhoneNumber(phoneNumber)
                .map(customer -> GenericResponse.success(
                        toDto(customer), "Customer retrieved successfully"))
                .orElseGet(() -> GenericResponse.failure("Customer not found"));
    }

    @GetMapping("/search/display-name")
    public GenericResponse<List<D>> findByDisplayName(
            @RequestParam String displayName) {
        return success(customerService.findByDisplayName(displayName));
    }

    @GetMapping("/search/status")
    public GenericResponse<List<D>> findByStatus(
            @RequestParam CustomerStatus status) {
        return success(customerService.findByStatus(status));
    }

    @GetMapping("/search/registration-channel")
    public GenericResponse<List<D>> findByRegistrationChannel(
            @RequestParam RegistrationChannel registrationChannel) {
        return success(customerService.findByRegistrationChannel(registrationChannel));
    }

    protected GenericResponse<List<D>> success(List<? extends Customer> customers) {
        return GenericResponse.success(
                customers.stream().map(this::toDto).toList(),
                "Customers retrieved successfully");
    }
}