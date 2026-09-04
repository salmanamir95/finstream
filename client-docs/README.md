# FinStream client documentation

This suite is written for business and integration audiences. **Current** means delivered in the inspected Account service; **planned** means not yet delivered. Internal implementation details are intentionally omitted.

## Current delivery boundary

The delivered capability is account-record management for current and savings accounts. The Account API runs locally on port `8080` and requires a separately provisioned PostgreSQL database named `finstream_account`. The Person module is not a client-facing API and currently has no HTTP controller.

There is no authentication, production deployment, customer ownership validation, balance or transfer processing, audit trail, stable error contract, or service-level agreement. Kafka is available only as local infrastructure and is not used by the Account API.
