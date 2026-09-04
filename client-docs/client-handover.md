# Client handover

Delivered: Account API for current/savings records and specialised settings. References: [API reference](04-api/api-reference.md), [integration guide](05-integration/integration-guide.md), and [security overview](06-security/security-overview.md).

Known limitations: no authentication, customer/ownership verification, transactions, balances, audit trail, stable error contract, explicit response status contract, deployment package, or defined support/SLA. The Account API also requires a separately provisioned PostgreSQL database; the included Compose stack provides Kafka and Kafka UI only. Deployment, security configuration, credentials, database availability, backups, monitoring, and support ownership must be completed before production handover.
