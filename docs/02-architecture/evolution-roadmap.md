# Architecture evolution roadmap

1. **Harden the Account service:** request/response models, validation, typed errors, explicit lifecycle rules, tests, database migration discipline, and no plaintext/default secrets.
2. **Protect access:** OIDC/JWT, customer ownership, audit events, TLS, rate limiting, and operating procedures.
3. **Build operational confidence:** container delivery, CI/CD, PostgreSQL integration tests, metrics/logs/traces, backups and restore drills.
4. **Add business boundaries selectively:** Customer then Transaction/Ledger when product workflows are defined; retain local ACID transactions per service.
5. **Introduce reliable events:** transactional outbox, schema/version governance, idempotent consumers, and observability.
6. **Scale production infrastructure:** gateway/ingress, managed HA data stores, and Kubernetes only if its operational benefits outweigh complexity.

This sequencing favours correctness and security before distribution. It is a proposed roadmap, not a release commitment.
