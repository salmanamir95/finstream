# Database design

| Table / column | Type | Constraint / purpose |
|---|---|---|
| `account.id` | BIGINT | primary key, auto increment |
| `account.customer_id` | BIGINT | non-null scalar customer reference |
| `account.account_number` | VARCHAR(50) | non-null, unique |
| `account.status` | VARCHAR(20) | non-null enum string |
| `current_account.id` | BIGINT | PK and FK to account |
| `current_account.overdraft_limit` | DECIMAL(19,2) | nullable |
| `savings_account.id` | BIGINT | PK and FK to account |
| `savings_account.interest_rate` | DECIMAL(5,2) | nullable |

The schema has no explicit index on `customer_id`, audit timestamps, version, check constraints, soft-delete field, or foreign key to a customer table. Add indexes only for measured query patterns; `customer_id` is a likely initial index. Use database decimal types, not floating point, for financial values.
