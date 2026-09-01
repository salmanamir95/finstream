# Sequence diagrams

## Create current account (current)

```mermaid
sequenceDiagram
  participant C as Client
  participant X as Current controller
  participant S as Current service
  participant R as Repository
  participant D as PostgreSQL
  C->>X: POST /api/accounts/current
  X->>S: create(input)
  S->>R: save(mapped entity)
  R->>D: INSERT account + current_account
  D-->>R: persisted entity
  S-->>X: CurrentAccountDTO
  X-->>C: 200 OK
```

For get/update/delete, the controller calls the specialised service, which queries/saves/deletes through its repository. Current specialised operations retrieve or update `overdraftLimit`; savings specialised operations retrieve or update `interestRate`.

## Future authenticated transaction

```mermaid
sequenceDiagram
  participant C as Client
  participant G as Gateway
  participant I as Identity provider
  participant T as Transaction service
  participant L as Ledger service
  C->>I: obtain token
  C->>G: transfer with token/idempotency key
  G->>T: authorised request
  T->>L: post balanced entries
  T-->>C: accepted/result
```

This is proposed, not implemented.
