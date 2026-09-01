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
public class CurrentAccount extends Account {

    @Column(precision = 19, scale = 2)
    private BigDecimal overdraftLimit;

    protected CurrentAccount() {
        super();
    }

    public CurrentAccount(
            Long customerId,
            String accountNumber,
            AccountStatus status,
            BigDecimal overdraftLimit) {

        super(customerId, accountNumber, status);
        this.overdraftLimit = overdraftLimit;
    }

    public BigDecimal getOverdraftLimit() {
    return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}