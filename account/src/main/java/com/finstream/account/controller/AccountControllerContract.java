package com.finstream.account.controller;

import java.util.List;

import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.common.response.GenericResponse;

public interface AccountControllerContract<
        D extends AccountDTO,
        I extends AccountInput> {

    GenericResponse<D> create(I input);

    GenericResponse<D> getById(Long id);

    GenericResponse<List<D>> getAll();

    GenericResponse<D> update(Long id, I input);

    GenericResponse<Void> delete(Long id);
}