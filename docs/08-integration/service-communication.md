# Service communication

Current Account operations are synchronous HTTP-to-database only. Person is not called by Account, and there are no application-level service-to-service clients. Kafka is available as local infrastructure but has no application integration: no topics, producers, consumers, outbox, retries, or dead-letter flow are implemented. Future synchronous calls should be limited to queries that need immediate answers, time-bounded, authenticated, and resilient. Avoid a web of direct service calls.
