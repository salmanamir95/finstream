package com.finstream.account.inputsAndDTOs;

import com.finstream.account.enums.AccountStatus;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class AccountDTO { private Long customerId; private String accountNumber; private AccountStatus status; }