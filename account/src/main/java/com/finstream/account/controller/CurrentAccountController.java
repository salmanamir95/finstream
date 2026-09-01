package com.finstream.account.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.CurrentAccountDTO;
import com.finstream.account.service.CurrentAccountService;

@RestController
@RequestMapping("/api/accounts/current")
public class CurrentAccountController
        extends AbstractAccountController<CurrentAccountDTO, AccountInput> {
    private final CurrentAccountService currentAccountService;

    public CurrentAccountController(
            CurrentAccountService currentAccountService) {

        super(currentAccountService);

        this.currentAccountService = currentAccountService;
    }

    @GetMapping("/{id}/overdraft-limit")
    public ResponseEntity<BigDecimal> getOverdraftLimit(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                currentAccountService.getOverdraftLimit(id));
    }

    @PatchMapping("/{id}/overdraft-limit/increase")
    public ResponseEntity<CurrentAccountDTO> increaseOverdraftLimit(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {

        return ResponseEntity.ok(
                currentAccountService.increaseOverdraftLimit(
                        id,
                        amount));
    }

}
