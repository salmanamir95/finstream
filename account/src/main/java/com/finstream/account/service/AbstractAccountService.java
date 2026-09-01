package com.finstream.account.service;

import java.util.List;

import com.finstream.account.domain.Account;
import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.repository.AccountRepository;

public abstract class AbstractAccountService<
        E extends Account,
        I extends AccountInput,
        D extends AccountDTO>
        implements AccountServiceContract<E, I, D> {

    protected final AccountRepository<E> repository;

    protected AbstractAccountService(
            AccountRepository<E> repository) {

        this.repository = repository;
    }

    @Override
    public D getById(Long id) {

        E account = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account not found: " + id));

        return toDTO(account);
    }

    @Override
    public List<D> getAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException(
                    "Account not found: " + id);
        }

        repository.deleteById(id);
    }

    protected abstract D toDTO(E account);

    @Override
    public abstract D create(I input);

    @Override
    public abstract D update(Long id, I input);
}