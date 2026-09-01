# Final architectural recommendation

## Must have now

Align parent versions; convert Common to a plain, small JAR; remove broad web/JPA/database/OpenAPI dependencies from Common; make root builds reproducible; and keep only implemented Account functionality documented as current.

## Should have soon

Centralise compiler/annotation-processor configuration in root plugin management; add dependency convergence/security checks; establish error/correlation conventions; introduce service-local production profiles and migrations; publish OpenAPI and contract tests.

## Future

Publish a FinStream BOM/Common artifact to an internal repository; add opt-in observability/security starters; add Customer and Transaction/Ledger only with defined product boundaries; use outbox events and database-per-service ownership.

## Avoid until needed

Shared entities/repositories, universal resilience policies, a giant Common module, shared service YAML, direct service-database access, distributed transactions, and Kubernetes/microservice proliferation without operating capacity.
