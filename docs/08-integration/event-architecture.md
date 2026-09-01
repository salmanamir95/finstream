# Event architecture — proposed

Use an outbox written in the same local transaction as the Account change. A publisher sends records to a broker; consumers deduplicate by `eventId`, tolerate retries, and route poison messages to a dead-letter topic.

| Event | Producer | Likely consumers | Notes |
|---|---|---|---|
| `AccountCreated` | Account service | audit, notification, customer view | key by account ID |
| `AccountUpdated` | Account service | audit, reporting | include version and changed fields |
| `AccountClosed` | Account service | transaction/payment policy | prevent future use |
| `TransactionCreated` | Transaction service | ledger, fraud, notification | future only |
| `PaymentInitiated/Completed/Failed` | Payment service | ledger, notification | future only |

Kafka is suited to durable high-throughput streams/replay; RabbitMQ suits command/work queues. Either needs schema governance, at-least-once handling, bounded retries, observability, and data-classification controls.
