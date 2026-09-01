package com.finstream.account.inputsAndDTOs;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SavingsAccountDTO extends AccountDTO { private BigDecimal interestRate; }