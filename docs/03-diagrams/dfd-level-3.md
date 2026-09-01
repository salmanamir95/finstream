# DFD Level 3 — Create account

```mermaid
flowchart TB
  A[Receive subtype POST] --> B[Bind AccountInput]
  B --> C[Choose endpoint-selected subtype]
  C --> D[Map customerId/accountNumber]
  D --> E[Save subtype and parent row]
  E --> F[Map saved entity to DTO]
  F --> G[Return HTTP 200 currently]
```

Important gap: `status`, overdraft limit, and interest rate are not supplied by `AccountInput`; subtype fields are initially null and status may violate the non-null schema constraint. Request validation, initial-status policy, duplicate handling, and `201 Created`/`Location` semantics are recommended.
