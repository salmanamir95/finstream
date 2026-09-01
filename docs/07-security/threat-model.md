# STRIDE threat model

| Threat | Vector | Risk | Mitigation | Residual risk |
|---|---|---:|---|---|
| Spoofing | Direct unprotected API calls | Critical | OIDC JWT validation, mTLS/workload identity | Token theft |
| Tampering | Modify another customer’s account | Critical | Resource ownership checks, audit trail, optimistic locking | Privileged misuse |
| Repudiation | No record of change | High | Immutable audit events with actor/correlation ID | Log availability |
| Information disclosure | Credentials/config/logs/errors | High | Secret manager, redaction, sanitised errors, TLS | Authorised access |
| Denial of service | Large/repeated requests | Medium | Gateway limits, timeouts, pool limits, WAF | Volumetric attacks |
| Elevation | Over-broad admin scope | Critical | Least privilege, segregation, approvals | Admin compromise |

Message broker, customer data, and transaction operations are future attack surfaces. Threat-model every new service/event before deployment.
