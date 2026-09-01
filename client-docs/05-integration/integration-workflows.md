# Integration workflows

```mermaid
sequenceDiagram
  participant C as Client system
  participant F as FinStream
  C->>F: Submit account request
  F-->>C: Account result or error
  C->>F: Retrieve/update specialised account
  F-->>C: Current account state
```

Future workflow: obtain access token → submit versioned request with idempotency key → receive result → reconcile safely on timeout. Events/webhooks are not currently provided.
