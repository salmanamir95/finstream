# Service communication

Current Account operations are synchronous HTTP-to-database only. Kafka is available as local infrastructure but has no application integration. Future synchronous calls should be limited to queries that need immediate answers, time-bounded, authenticated, and resilient. Avoid a web of direct service calls.
