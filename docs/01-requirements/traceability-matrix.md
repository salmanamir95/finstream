# Traceability matrix

| Business requirement | Functional requirement | API | Service/domain/database | Verification |
|---|---|---|---|---|
| BR-001 | FR-001/FR-004 | POST subtype routes | specialised service → subtype entity → `account` + child table | TEST-001 integration test |
| BR-002 | FR-003 | overdraft routes | `CurrentAccountService` / `CurrentAccount.overdraftLimit` | TEST-002 service + API test |
| BR-003 | FR-006 | interest routes | `SavingsAccountService` / `SavingsAccount.interestRate` | TEST-003 service + API test |
| BR-004 | FR-001/FR-004 | create routes | `account.account_number` unique constraint | TEST-004 duplicate-conflict test |
| BR-005 | FR-007/FR-008 | all routes | security policy / error handler | TEST-005 authorisation and error-contract tests (proposed) |

`TEST-001`–`TEST-005` name recommended tests; only a Spring context smoke test currently exists.
