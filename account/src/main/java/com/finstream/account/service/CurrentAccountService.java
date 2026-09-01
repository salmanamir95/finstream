package com.finstream.account.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.finstream.account.domain.CurrentAccount;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.CurrentAccountDTO;
import com.finstream.account.mappers.CurrentAccountMapper;
import com.finstream.account.repository.CurrentAccountRepository;

@Service
public class CurrentAccountService
        extends AbstractAccountService<
                CurrentAccount,
                AccountInput,
                CurrentAccountDTO> {

    private final CurrentAccountRepository currentAccountRepository;
    private final CurrentAccountMapper currentAccountMapper;

    public CurrentAccountService(
            CurrentAccountRepository currentAccountRepository,
            CurrentAccountMapper currentAccountMapper) {

        super(currentAccountRepository);

        this.currentAccountRepository = currentAccountRepository;
        this.currentAccountMapper = currentAccountMapper;
    }

    @Override
    protected CurrentAccountDTO toDTO(CurrentAccount account) {

        return currentAccountMapper.toDTO(account);
    }

    @Override
    public CurrentAccountDTO create(AccountInput input) {

        CurrentAccount account =
                currentAccountMapper.toEntity(input);

        CurrentAccount savedAccount =
                currentAccountRepository.save(account);

        return currentAccountMapper.toDTO(savedAccount);
    }

    @Override
    public CurrentAccountDTO update(
            Long id,
            AccountInput input) {

        CurrentAccount account =
                currentAccountRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Current account not found: " + id));

        currentAccountMapper.updateEntity(input, account);

        CurrentAccount updatedAccount =
                currentAccountRepository.save(account);

        return currentAccountMapper.toDTO(updatedAccount);
    }

    public BigDecimal getOverdraftLimit(Long id) {

        CurrentAccount account =
                currentAccountRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Current account not found: " + id));

        return account.getOverdraftLimit();
    }

    public CurrentAccountDTO increaseOverdraftLimit(
            Long id,
            BigDecimal amount) {

        if (amount == null ||
                amount.compareTo(BigDecimal.ZERO) <= 0) {

            throw new IllegalArgumentException(
                    "Amount must be greater than zero");
        }

        CurrentAccount account =
                currentAccountRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Current account not found: " + id));

        BigDecimal currentLimit =
                account.getOverdraftLimit();

        if (currentLimit == null) {
            currentLimit = BigDecimal.ZERO;
        }

        account.setOverdraftLimit(
                currentLimit.add(amount));

        CurrentAccount updatedAccount =
                currentAccountRepository.save(account);

        return currentAccountMapper.toDTO(updatedAccount);
    }
}