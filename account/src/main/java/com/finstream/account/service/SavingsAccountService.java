
package com.finstream.account.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.finstream.account.domain.SavingsAccount;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.SavingsAccountDTO;
import com.finstream.account.mappers.SavingsAccountMapper;
import com.finstream.account.repository.SavingsAccountRepository;

@Service
public class SavingsAccountService
        extends AbstractAccountService<
                SavingsAccount,
                AccountInput,
                SavingsAccountDTO> {

    private final SavingsAccountRepository savingsAccountRepository;
    private final SavingsAccountMapper savingsAccountMapper;

    public SavingsAccountService(
            SavingsAccountRepository savingsAccountRepository,
            SavingsAccountMapper savingsAccountMapper) {

        super(savingsAccountRepository);

        this.savingsAccountRepository = savingsAccountRepository;
        this.savingsAccountMapper = savingsAccountMapper;
    }

    @Override
    protected SavingsAccountDTO toDTO(SavingsAccount account) {

        return savingsAccountMapper.toDTO(account);
    }

    @Override
    public SavingsAccountDTO create(AccountInput input) {

        SavingsAccount account =
                savingsAccountMapper.toEntity(input);

        SavingsAccount savedAccount =
                savingsAccountRepository.save(account);

        return savingsAccountMapper.toDTO(savedAccount);
    }

    @Override
    public SavingsAccountDTO update(
            Long id,
            AccountInput input) {

        SavingsAccount account =
                savingsAccountRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Savings account not found: " + id));

        savingsAccountMapper.updateEntity(input, account);

        SavingsAccount updatedAccount =
                savingsAccountRepository.save(account);

        return savingsAccountMapper.toDTO(updatedAccount);
    }

    public BigDecimal getInterestRate(Long id) {

        SavingsAccount account =
                savingsAccountRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Savings account not found: " + id));

        return account.getInterestRate();
    }

    public SavingsAccountDTO updateInterestRate(
            Long id,
            BigDecimal interestRate) {

        if (interestRate == null ||
                interestRate.compareTo(BigDecimal.ZERO) < 0) {

            throw new IllegalArgumentException(
                    "Interest rate cannot be negative");
        }

        SavingsAccount account =
                savingsAccountRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Savings account not found: " + id));

        account.setInterestRate(interestRate);

        SavingsAccount updatedAccount =
                savingsAccountRepository.save(account);

        return savingsAccountMapper.toDTO(updatedAccount);
    }
}
