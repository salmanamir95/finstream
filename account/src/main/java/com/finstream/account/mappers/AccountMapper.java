package com.finstream.account.mappers;

import com.finstream.account.domain.Account;
import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;

public interface AccountMapper<
        E extends Account,
        I extends AccountInput,
        D extends AccountDTO> {

    D toDTO(E entity);

    E toEntity(I input);

    void updateEntity(I input, E entity);
}