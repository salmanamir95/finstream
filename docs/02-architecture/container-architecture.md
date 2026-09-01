# Container architecture

| Container | Status | Responsibility |
|---|---|---|
| Account Service | Confirmed | REST API and account persistence. |
| Account PostgreSQL | Configured/inferred | Stores account tables; local endpoint is configured. |
| Kafka + Kafka UI | Confirmed infrastructure | Local broker/admin UI; not connected to application code. |
| API gateway, identity, customer, transaction, ledger, payment, notification, audit | Proposed | Future platform containers. |

For production, deploy the Account service as a stateless container behind an ingress/gateway; use managed or highly available PostgreSQL, externally managed secrets, and a private network path to the database. Kubernetes is a deployment option, not a current implementation.
