# Non-functional requirements

These are **proposed targets requiring business confirmation**, not current guarantees.

| ID | Category | Proposed measurable target |
|---|---|---|
| NFR-001 | Availability | 99.9% monthly availability excluding agreed maintenance. |
| NFR-002 | Performance | p95 read latency under 200 ms and write latency under 500 ms at agreed load. |
| NFR-003 | Integrity | No committed duplicate account number; all account writes are atomic. |
| NFR-004 | Security | TLS in transit; least-privilege identity checks for every account access. |
| NFR-005 | Auditability | Immutable audit event for privileged/account-state changes. |
| NFR-006 | Recoverability | RPO/RTO to be agreed; test restores at least quarterly. |
| NFR-007 | Observability | Health, structured logs, metrics, and trace correlation on all production requests. |
| NFR-008 | Maintainability | PR review, automated tests, schema migrations, and versioned API contracts. |

Capacity is unknown. Plan from measured traffic, account growth, read/write ratio, payload sizes, and recovery windows—not assumptions presented as facts.
