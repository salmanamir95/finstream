package com.finstream.account.service;

import java.util.List;

import com.finstream.account.domain.Account;
import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;

public interface AccountServiceContract<
        E extends Account,
        I extends AccountInput,
        D extends AccountDTO> {

    D create(I input);

    D getById(Long id);

    List<D> getAll();

    D update(Long id, I input);

    void delete(Long id);
}