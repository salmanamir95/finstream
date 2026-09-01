package com.finstream.account.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.finstream.account.domain.SavingsAccount;
import com.finstream.account.inputsAndDTOs.AccountInput;
import com.finstream.account.inputsAndDTOs.SavingsAccountDTO;

@Mapper(componentModel = "spring")
public interface SavingsAccountMapper {

    SavingsAccountDTO toDTO(SavingsAccount account);

    SavingsAccount toEntity(AccountInput input);

    void updateEntity(
            AccountInput input,
            @MappingTarget SavingsAccount account);
}
