package com.finstream.account.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.SavingsAccountDTO;
import com.finstream.account.service.SavingsAccountService;
import com.finstream.common.response.GenericResponse;

@RestController
@RequestMapping("/api/accounts/savings")
public class SavingsAccountController
        extends AbstractAccountController<
                SavingsAccountDTO,
                AccountInput> {

    private final SavingsAccountService savingsAccountService;

    public SavingsAccountController(
            SavingsAccountService savingsAccountService) {

        super(savingsAccountService);

        this.savingsAccountService = savingsAccountService;
    }

    @GetMapping("/{id}/interest-rate")
    public GenericResponse<BigDecimal> getInterestRate(
            @PathVariable Long id) {

        return GenericResponse.success(
                savingsAccountService.getInterestRate(id),
                "Interest rate retrieved successfully");
    }

    @PatchMapping("/{id}/interest-rate")
    public GenericResponse<SavingsAccountDTO> updateInterestRate(
            @PathVariable Long id,
            @RequestParam BigDecimal interestRate) {

        return GenericResponse.success(
                savingsAccountService.updateInterestRate(
                        id,
                        interestRate),
                "Interest rate updated successfully");
    }
}
