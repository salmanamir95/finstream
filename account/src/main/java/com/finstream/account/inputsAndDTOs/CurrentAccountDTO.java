package com.finstream.account.inputsAndDTOs;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
public class CurrentAccountDTO extends AccountDTO { private BigDecimal overdraftLimit; }