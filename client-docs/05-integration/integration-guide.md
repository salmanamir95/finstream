# Integration guide

Integrate through the published REST endpoints using JSON. Match the account subtype to the endpoint, validate responses, use timeouts, and keep retries bounded. Create operations are not idempotent today; until an idempotency-key contract exists, reconcile an uncertain result before retrying. Authentication, production base URL, rate limits, and SLAs require agreement.
