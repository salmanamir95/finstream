package com.finstream.account.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.finstream.account.domain.CurrentAccount;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.CurrentAccountDTO;

@Mapper(componentModel = "spring")
public interface CurrentAccountMapper {

    CurrentAccountDTO toDTO(CurrentAccount account);

    CurrentAccount toEntity(AccountInput input);

    void updateEntity(
            AccountInput input,
            @MappingTarget CurrentAccount account);
}
