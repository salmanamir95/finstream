# Domain model

`Account` is an abstract base with `id`, `customerId`, unique `accountNumber`, and `status`. `CurrentAccount` adds nullable `overdraftLimit`; `SavingsAccount` adds nullable `interestRate`. There are no entity relationships to a Customer entity: `customerId` is a scalar external reference.

Invariants currently enforced by database mapping are non-null customer ID, account number, and status; unique account number; and subtype rows referencing an account. Application code only enforces a positive increase amount and non-negative replacement interest rate. It does not validate initial values, status transitions, ownership, or account-number format.
