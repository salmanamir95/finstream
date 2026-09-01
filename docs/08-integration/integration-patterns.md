# Integration patterns

Use API contracts for synchronous external interaction and versioned domain events for asynchronous facts. Apply idempotency keys to create/financial commands, exponential backoff with jitter for transient errors, explicit timeouts, and a Saga for multi-service work. A Saga coordinates local commits and compensations; it is not a replacement for a double-entry ledger or an audit trail.
