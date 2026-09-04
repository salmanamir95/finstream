# Service overview

The delivered Account service manages account records on port `8080` and exposes health and info actuator endpoints. It depends on PostgreSQL at the configured `finstream_account` database; the database is not provisioned by the repository's Docker Compose file. Kafka infrastructure exists for local learning but is not a dependency of current account operations. Production monitoring, on-call coverage, backups, deployment packaging, and service objectives are to be agreed.
