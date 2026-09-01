# Architecture review and scorecard

| Area | Score | Evidence and improvement |
|---|---:|---|
| Architecture | 5/10 | Clear layered Account service; no bounded-context integration/security strategy implemented. Add use cases and ownership rules. |
| Code organisation | 6/10 | Packages and type-specific layers are clear; DTO package/name is awkward and controller contract unused. |
| Maintainability | 5/10 | Mapper/service separation helps; duplicate CRUD and generic runtime exceptions hurt. |
| Scalability | 3/10 | Stateless shape is promising; no measured capacity, pool tuning, paging, caching, or deployment topology. |
| Security | 1/10 | No authentication/authorisation; credentials in config. |
| Observability | 2/10 | Actuator health/info configured; no structured logs, metrics dashboards, traces. |
| Testing | 1/10 | Only application-context test found. |
| API design | 4/10 | Basic semantics; POST returns 200 rather than 201, no validation/error contract/versioning. |
| Database design | 5/10 | Unique account number and JOINED tables; no audit/version/index strategy; conflicting schema management. |
| Domain modelling | 4/10 | Useful subtype model; status is not set by input and no lifecycle rules enforce it. |
| DevOps readiness | 2/10 | Kafka compose only; Account service/database not composed, no pipeline. |
| Documentation | 6/10 | This package documents gaps; keep it maintained with changes. |
| Production readiness | 1/10 | Not appropriate for financial production until P0/P1 work is complete. |

Priorities: **P0:** remove plaintext/default secrets, add authz/customer ownership, validation, standard error handling, and real transaction/audit rules. **P1:** disable Hibernate schema update in production; add tests, optimistic locking, audit fields, idempotency and pagination. **P2:** OpenTelemetry/metrics, outbox events, CI/CD, container deployment. **P3:** reconsider inheritance as domain rules grow.
