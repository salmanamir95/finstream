package com.finstream.account.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.finstream.account.inputsAndDTOs.AccountDTO;
import com.finstream.account.inputsAndDTOs.AccountInput;

public interface AccountControllerContract<
        D extends AccountDTO,
        I extends AccountInput> {

    ResponseEntity<D> create(I input);

    ResponseEntity<D> getById(Long id);

    ResponseEntity<List<D>> getAll();

    ResponseEntity<D> update(Long id, I input);

    ResponseEntity<Void> delete(Long id);
}