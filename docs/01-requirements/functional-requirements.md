# Functional requirements

| ID | Requirement | API / implementation evidence | Status |
|---|---|---|---|
| FR-001 | Create a current account. | `POST /api/accounts/current` | Confirmed |
| FR-002 | Retrieve/list/update/delete current accounts. | Current controller CRUD routes | Confirmed |
| FR-003 | Read/increase an overdraft limit. | Current specialised routes | Confirmed |
| FR-004 | Create a savings account. | `POST /api/accounts/savings` | Confirmed |
| FR-005 | Retrieve/list/update/delete savings accounts. | Savings controller CRUD routes | Confirmed |
| FR-006 | Read/update an interest rate. | Savings specialised routes | Confirmed |
| FR-007 | Validate structural request fields. | No Bean Validation annotations or `@Valid` use | Proposed |
| FR-008 | Return typed, stable API errors. | Runtime exceptions propagate without controller advice | Proposed |

The generic `/api/accounts` endpoints cited in the original request do **not** exist. The actual endpoint contract is documented in [Account API](../05-api/account-api.md).
