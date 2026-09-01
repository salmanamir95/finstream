# Entity relationship diagram

```mermaid
erDiagram
  ACCOUNT ||--o| CURRENT_ACCOUNT : "joined subtype"
  ACCOUNT ||--o| SAVINGS_ACCOUNT : "joined subtype"
  ACCOUNT {
    BIGINT id PK
    BIGINT customer_id
    VARCHAR account_number UK
    VARCHAR status
  }
  CURRENT_ACCOUNT { BIGINT id PK_FK DECIMAL overdraft_limit }
  SAVINGS_ACCOUNT { BIGINT id PK_FK DECIMAL interest_rate }
```
