# Account domain details

| Object | Purpose | Attributes | Lifecycle / behaviour |
|---|---|---|---|
| Account | Shared persisted account identity/state | id, customerId, accountNumber, status | Status enum exists; transition policy is proposed. |
| CurrentAccount | Overdraft-enabled subtype | inherited fields + overdraftLimit | Limit can be read and incremented; no balance/overdraft use exists. |
| SavingsAccount | Interest-bearing subtype | inherited fields + interestRate | Rate can be read/replaced; no interest accrual exists. |

Invalid states to prevent in a hardened release: null/unknown customer; duplicate/malformed account number; null initial status; negative overdraft limit; negative or policy-exceeding rate; changes to closed accounts; physical deletion after financial activity. These are recommendations except where the database constraint already applies.
