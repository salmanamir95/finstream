# Deployment

Local development: run PostgreSQL compatible with the configured datasource, then `cd account && ./mvnw spring-boot:run`; service port is 8080. The repository does not provide a Compose service for Account or PostgreSQL.

Production proposal: build an immutable application image, inject configuration/secrets at runtime, run multiple stateless replicas behind TLS ingress, use managed/HA PostgreSQL, and apply Liquibase before compatible code relies on schema changes. Kubernetes can provide scheduling/probes/rollout controls but adds operational complexity; choose it only with sufficient operations capability.
