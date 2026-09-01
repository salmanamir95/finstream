# Business requirements

| ID | Requirement | Status |
|---|---|---|
| BR-001 | Maintain accounts associated with a customer identifier. | Partially implemented: identifier is stored, not validated. |
| BR-002 | Support current accounts and an overdraft configuration. | Implemented. |
| BR-003 | Support savings accounts and an interest-rate configuration. | Implemented. |
| BR-004 | Keep account numbers unique. | Implemented by schema constraint. |
| BR-005 | Permit authorised personnel/customers to manage only permitted accounts. | Proposed. |
| BR-006 | Preserve an auditable financial history. | Proposed. |

Success criteria for the current learning release: valid requests persist and return the correct account subtype; duplicate account numbers are rejected predictably; all endpoints have tested, documented behaviour. The last two require hardening before they are demonstrably met.
