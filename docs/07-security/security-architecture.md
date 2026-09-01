# Security architecture

## Current

No authentication, authorisation, TLS configuration, ownership checks, secrets manager, audit log, or rate limiting is evidenced. Database credentials appear in `application.yaml`; treat them as development defaults and rotate them if ever used beyond local development.

## Recommended

Use OAuth2/OIDC with JWT access tokens validated at a gateway and service. Enforce scopes/roles plus resource-level customer ownership in the Account service; privileged changes (rate/overdraft/status) require distinct permissions and audit events. Use TLS externally and internally where required, short-lived database credentials/secrets injected by a secret manager, least-privilege database roles, parameterised JPA queries, validated DTO allow-lists, rate limits, and redacted structured logs.

Service-to-service calls should use workload identity or OAuth client credentials, not shared static tokens. Password storage is out of scope for Account service; if identity stores passwords, use a modern adaptive one-way hash under the identity provider’s controls.
