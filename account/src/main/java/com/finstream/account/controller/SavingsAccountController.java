package com.finstream.account.controller;

import java.math.BigDecimal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.SavingsAccountDTO;
import com.finstream.account.service.SavingsAccountService;

@RestController
@RequestMapping("/api/accounts/savings")
public class SavingsAccountController
                extends AbstractAccountController<SavingsAccountDTO, AccountInput> {

        private final SavingsAccountService savingsAccountService;

        public SavingsAccountController(
                        SavingsAccountService savingsAccountService) {

                super(savingsAccountService);

                this.savingsAccountService = savingsAccountService;
        }

        @GetMapping("/{id}/interest-rate")
        public ResponseEntity<BigDecimal> getInterestRate(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                savingsAccountService.getInterestRate(id));
        }

        @PatchMapping("/{id}/interest-rate")
        public ResponseEntity<SavingsAccountDTO> updateInterestRate(
                        @PathVariable Long id,
                        @RequestParam BigDecimal interestRate) {

                return ResponseEntity.ok(
                                savingsAccountService.updateInterestRate(
                                                id,
                                                interestRate));
        }

}
