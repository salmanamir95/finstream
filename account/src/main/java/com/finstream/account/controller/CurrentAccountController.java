package com.finstream.account.controller;

import java.math.BigDecimal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.CurrentAccountDTO;
import com.finstream.account.service.CurrentAccountService;
import com.finstream.common.response.GenericResponse;

@RestController
@RequestMapping("/api/accounts/current")
public class CurrentAccountController
        extends AbstractAccountController<
                CurrentAccountDTO,
                AccountInput> {

    private final CurrentAccountService currentAccountService;

    public CurrentAccountController(
            CurrentAccountService currentAccountService) {

        super(currentAccountService);

        this.currentAccountService = currentAccountService;
    }

    @GetMapping("/{id}/overdraft-limit")
    public GenericResponse<BigDecimal> getOverdraftLimit(
            @PathVariable Long id) {

        return GenericResponse.success(
                currentAccountService.getOverdraftLimit(id),
                "Overdraft limit retrieved successfully");
    }

    @PatchMapping("/{id}/overdraft-limit/increase")
    public GenericResponse<CurrentAccountDTO> increaseOverdraftLimit(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {

        return GenericResponse.success(
                currentAccountService.increaseOverdraftLimit(
                        id,
                        amount),
                "Overdraft limit increased successfully");
    }
}
