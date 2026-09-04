# Data architecture

Account service owns account data. PostgreSQL is configured through `spring.datasource`; the Account Spring context test connected to the configured local PostgreSQL instance on 4 September 2026. No database-per-service boundary is yet demonstrated across multiple implemented services. The Person service has a separate configured database but no client-facing API.

Each business write should have an explicit `@Transactional` application-service boundary. For concurrent updates, add `@Version` optimistic locking to Account and translate stale writes to 409. Use pessimistic locking only for short, demonstrated contention cases. Future cross-service operations should use local transactions plus Saga/outbox events, not two-phase distributed transactions.
