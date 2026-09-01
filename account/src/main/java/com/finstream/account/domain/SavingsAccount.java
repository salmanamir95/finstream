package com.finstream.account.domain;

import java.math.BigDecimal;

import com.finstream.account.enums.AccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SavingsAccount extends Account {

    @Column(precision = 5, scale = 2)
    private BigDecimal interestRate;

    protected SavingsAccount() {
        super();
    }

    public SavingsAccount(
            Long customerId,
            String accountNumber,
            AccountStatus status,
            BigDecimal interestRate) {

        super(customerId, accountNumber, status);
        this.interestRate = interestRate;
    }

    public BigDecimal getInterestRate() {
    return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}