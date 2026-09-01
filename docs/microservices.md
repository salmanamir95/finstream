# 1. Final FinStream architecture

```text
                         ┌──────────────────────┐
                         │      Client/UI       │
                         └──────────┬───────────┘
                                    │
                                    │ HTTP
                                    ▼
                         ┌──────────────────────┐
                         │     API Gateway      │
                         │   Spring Cloud GW    │
                         └──────────┬───────────┘
                                    │
                                    ▼
                         ┌──────────────────────┐
                         │  Transaction Service │
                         │    Spring Boot       │
                         └──────────┬───────────┘
                                    │
                       ┌────────────┴────────────┐
                       │                         │
                       ▼                         ▼
                ┌─────────────┐           ┌──────────────┐
                │ PostgreSQL  │           │ Outbox Table │
                └─────────────┘           └──────┬───────┘
                                                 │
                                                 ▼
                                        ┌─────────────────┐
                                        │ Outbox Publisher │
                                        └────────┬────────┘
                                                 │
                                                 ▼
══════════════════════════════════════════════════════════════════
                         APACHE KAFKA
══════════════════════════════════════════════════════════════════
                                                 │
                    ┌────────────────────────────┼─────────────────────┐
                    │                            │                     │
                    ▼                            ▼                     ▼
             ┌───────────────┐          ┌──────────────┐       ┌─────────────┐
             │ Fraud Service │          │ Notification │       │  Analytics  │
             │    Drools     │          │   Service    │       │   Service   │
             └───────┬───────┘          │    Novu      │       │    Pinot    │
                     │                  └──────────────┘       └─────────────┘
                     │
                     ▼
              fraud.events
                     │
                     ▼
             Transaction Service
```

The key idea is:

> **REST enters through the gateway; Kafka handles asynchronous communication between capabilities.**

---

# 2. Services / microservices

I'd use these:

| # | Service                  | Technology                | Main responsibility                    |
| - | ------------------------ | ------------------------- | -------------------------------------- |
| 1 | **API Gateway**          | Spring Cloud Gateway      | Entry point, routing                   |
| 2 | **Transaction Service**  | Spring Boot               | Accounts, transfers, transaction state |
| 3 | **Fraud Service**        | Spring Boot + Drools      | Fraud/risk decisions                   |
| 4 | **Notification Service** | Spring Boot + Novu        | Notifications                          |
| 5 | **Analytics Service**    | Kafka + Pinot integration | Real-time analytics                    |

And infrastructure:

| Infrastructure | Purpose                       |
| -------------- | ----------------------------- |
| PostgreSQL     | Transactional data            |
| Kafka          | Event backbone                |
| Kafka UI       | Kafka administration/learning |
| Pinot          | Analytical store              |
| Novu           | Notification delivery         |
| Docker         | Local infrastructure          |

---

# 3. API Gateway

### Service

```text
api-gateway
```

Technology:

```text
Spring Cloud Gateway
```

Port:

```text
8080
```

Its job is **not** to contain business logic.

It routes:

```text
/api/v1/accounts/**

        ↓

transaction-service
```

and:

```text
/api/v1/transfers/**

        ↓

transaction-service
```

Potentially:

```text
/api/v1/analytics/**

        ↓

analytics-service
```

### Gateway responsibilities

```text
Client
  │
  ▼
Gateway
  │
  ├── Authentication later
  ├── Rate limiting later
  ├── Request routing
  ├── Correlation ID
  └── Logging
```

For the first version, keep it simple.

---

# 4. Transaction Service

This is the **core service**.

```text
transaction-service
```

This service owns:

### Account

```text
Account
├── accountId
├── userId
├── currency
├── balance
└── status
```

### Transaction

```text
Transaction
├── transactionId
├── fromAccountId
├── toAccountId
├── amount
├── currency
├── status
├── createdAt
└── updatedAt
```

### Outbox

```text
OutboxEvent
├── eventId
├── aggregateId
├── eventType
├── payload
├── status
├── createdAt
└── publishedAt
```

---

# 5. Transaction Service — producers

This service produces:

### Producer 1

```text
TransactionEventProducer
```

Publishes:

```text
transaction.events
```

Events:

```text
TRANSACTION_CREATED
TRANSACTION_COMPLETED
TRANSACTION_FAILED
```

Initially, I'd only implement:

```text
TRANSACTION_CREATED
```

Then add the others.

---

# 6. Transaction Service — consumers

The Transaction Service also needs to consume:

```text
fraud.events
```

Consumer:

```text
FraudDecisionConsumer
```

It receives:

```json
{
  "transactionId": "txn-123",
  "decision": "BLOCKED",
  "riskScore": 91
}
```

Then updates:

```text
Transaction.status
```

For example:

```text
PENDING_FRAUD
      │
      ├── APPROVED ──► COMPLETED
      │
      └── BLOCKED ───► BLOCKED
```

This is important because it demonstrates **asynchronous state transitions**.

---

# 7. Fraud Service

```text
fraud-service
```

Technology:

```text
Spring Boot
Spring Kafka
Drools
```

Its responsibility:

> Consume transaction events and produce fraud decisions.

---

## Fraud Service consumer

Consumer group:

```text
fraud-service-group
```

Consumes:

```text
transaction.events
```

Flow:

```text
transaction.events
        │
        ▼
Fraud Consumer
        │
        ▼
Drools
        │
        ▼
Fraud Decision
```

---

# 8. Fraud Service producer

Producer:

```text
FraudEventProducer
```

Produces:

```text
fraud.events
```

Example:

```json
{
  "eventId": "fraud-123",
  "transactionId": "txn-123",
  "decision": "BLOCKED",
  "riskScore": 91,
  "reason": "HIGH_AMOUNT"
}
```

---

# 9. Notification Service

```text
notification-service
```

Technology:

```text
Spring Boot
Spring Kafka
Novu
```

This service should **not** be responsible for deciding whether a transaction is fraudulent.

It only reacts to events.

---

# 10. Notification Service consumers

I'd actually give it **two consumers**.

### Consumer A

```text
TransactionNotificationConsumer
```

Consumes:

```text
transaction.events
```

Consumer group:

```text
notification-service-group
```

For example:

```text
TRANSACTION_COMPLETED
        │
        ▼
Notification Service
        │
        ▼
Novu
        │
        ├── Email
        └── In-app
```

### Consumer B

```text
FraudNotificationConsumer
```

Consumes:

```text
fraud.events
```

Same consumer group:

```text
notification-service-group
```

This lets it handle:

```text
FRAUD_DETECTED
FRAUD_BLOCKED
```

and send appropriate notifications.

---

# 11. Analytics Service

Here I'd simplify your original design slightly.

You don't necessarily need a large Spring Boot service just to push every event into Pinot.

For your portfolio project, you can have:

```text
analytics-service
```

responsible for:

```text
Kafka → Pinot
```

and optionally expose:

```text
GET /api/v1/analytics/...
```

for dashboard queries.

---

# 12. Analytics Service consumer

Consumer:

```text
TransactionAnalyticsConsumer
```

Consumer group:

```text
analytics-service-group
```

Consumes:

```text
transaction.events
```

Potentially also:

```text
fraud.events
```

I'd recommend consuming **both**.

Why?

Because then you can build metrics such as:

```text
Total transactions
Transaction volume
Completed transactions
Failed transactions
Blocked transactions
Fraud rate
Average transaction amount
```

---

# 13. Producers and consumers — complete list

Here's the clean version.

| Service      | Producer                   | Topic                | Consumer                          |
| ------------ | -------------------------- | -------------------- | --------------------------------- |
| Transaction  | `TransactionEventProducer` | `transaction.events` | `FraudDecisionConsumer`           |
| Fraud        | `FraudEventProducer`       | `fraud.events`       | `TransactionEventConsumer`        |
| Notification | none initially             | —                    | `TransactionNotificationConsumer` |
| Notification | none initially             | —                    | `FraudNotificationConsumer`       |
| Analytics    | none initially             | —                    | `TransactionAnalyticsConsumer`    |
| Gateway      | none                       | —                    | none                              |

Notice something important:

**Kafka producers/consumers are application components, not necessarily separate microservices.**

That's an important distinction.

---

# 14. Final Kafka topics

I would **not create every topic mentioned in your README immediately**.

Start with these:

```text
transaction.events
fraud.events
```

Then introduce failure topics:

```text
transaction.events.DLT
fraud.events.DLT
```

For notifications:

```text
notification.events
notification.events.DLT
```

But there's an architectural point here:

If the Notification Service consumes `transaction.events` and `fraud.events`, you don't actually need `notification.events` initially.

So my recommended initial topic set is:

```text
transaction.events
fraud.events

transaction.events.DLT
fraud.events.DLT

notification.events.DLT
```

You can add retry topics later if you specifically want to demonstrate retry-topic patterns.

---

# 15. Topic ownership

This is useful to understand.

### `transaction.events`

**Produced by:**

```text
Transaction Service
```

**Consumed by:**

```text
Fraud Service
Notification Service
Analytics Service
```

---

### `fraud.events`

**Produced by:**

```text
Fraud Service
```

**Consumed by:**

```text
Transaction Service
Notification Service
Analytics Service
```

---

# 16. Consumer groups

This is where Kafka gets interesting.

You have:

```text
transaction.events
       │
       ├────────────── fraud-service-group
       │
       ├────────────── notification-service-group
       │
       └────────────── analytics-service-group
```

Each group gets its **own copy of the logical consumption position**.

Therefore:

```text
Transaction event #100
```

can be processed independently by:

```text
Fraud Service       → offset 100
Notification        → offset 100
Analytics           → offset 100
```

They don't interfere with each other.

That's one of the most important Kafka concepts your project should demonstrate.

---

# 17. Partitions

Now let's decide the actual partition strategy.

For local development:

```text
transaction.events
├── Partition 0
├── Partition 1
└── Partition 2
```

I'd start with **3 partitions**.

Same for:

```text
fraud.events
├── Partition 0
├── Partition 1
└── Partition 2
```

Why 3?

Because it gives you enough room to demonstrate:

* parallel consumers
* partition assignment
* ordering
* consumer groups
* scaling

without creating unnecessary infrastructure.

---

# 18. Partition key

For:

```text
transaction.events
```

use:

```text
accountId
```

as the Kafka message key.

Example:

```text
account-A → Partition 0
account-B → Partition 2
account-C → Partition 1
account-A → Partition 0
account-A → Partition 0
```

Therefore:

```text
account-A

TX1
TX2
TX3
TX4
```

will maintain ordering **within its partition**.

That's extremely valuable for your FinTech example.

---

# 19. But there's a subtle problem

Your event has:

```text
fromAccountId
toAccountId
```

Which account should determine the partition?

For your first implementation, use:

```text
accountId = fromAccountId
```

or define a canonical event field:

```json
{
  "accountId": "ACC-1001",
  "fromAccountId": "ACC-1001",
  "toAccountId": "ACC-2001"
}
```

and use:

```text
key = accountId
```

This makes your Kafka demonstrations much easier.

Later you can discuss the deeper problem of **ordering across two accounts** in transfers.

That's actually a good interview discussion.

---

# 20. Consumer scaling demonstration

Suppose:

```text
transaction.events
```

has:

```text
P0
P1
P2
```

And:

```text
fraud-service-group
```

has one consumer:

```text
Fraud Consumer 1

P0
P1
P2
```

One consumer handles all three partitions.

Now start another instance:

```text
Fraud Consumer 1 → P0, P1
Fraud Consumer 2 → P2
```

Start a third:

```text
Fraud Consumer 1 → P0
Fraud Consumer 2 → P1
Fraud Consumer 3 → P2
```

Now you've **visibly demonstrated Kafka horizontal scaling**.

That's an excellent thing to show in your LinkedIn video later.

---

# 21. What happens if you start a fourth consumer?

You only have three partitions.

So:

```text
P0 → Consumer 1
P1 → Consumer 2
P2 → Consumer 3
```

Consumer 4 gets:

```text
nothing
```

This teaches a fundamental Kafka rule:

> **Within a consumer group, a partition can be assigned to only one consumer at a time.**

Therefore:

```text
3 partitions
```

means at most:

```text
3 actively consuming consumers
```

within that group.

---

# 22. Complete architecture by responsibility

Here's the architecture I'd actually build.

```text
                         CLIENT
                           │
                           ▼
                  ┌─────────────────┐
                  │   API Gateway   │
                  │      :8080      │
                  └────────┬────────┘
                           │
                           ▼
               ┌───────────────────────┐
               │ Transaction Service   │
               │       :8081           │
               └──────────┬────────────┘
                          │
              ┌───────────┴────────────┐
              │                        │
              ▼                        ▼
       ┌──────────────┐        ┌──────────────┐
       │  PostgreSQL  │        │    Outbox    │
       │              │        │    Table     │
       └──────────────┘        └──────┬───────┘
                                      │
                                      ▼
                               Outbox Publisher
                                      │
                                      ▼
                         ╔══════════════════════╗
                         ║        KAFKA         ║
                         ║                      ║
                         ║ transaction.events   ║
                         ║                      ║
                         ╚══════════╤═══════════╝
                                    │
             ┌──────────────────────┼─────────────────────┐
             │                      │                     │
             ▼                      ▼                     ▼
       fraud-group          notification-group    analytics-group
             │                      │                     │
             ▼                      ▼                     ▼
      Fraud Service          Notification Service  Analytics Service
             │                      │                     │
          Drools                    │                   Pinot
             │                      ▼
             │                     Novu
             │
             ▼
       fraud.events
             │
      ┌──────┼─────────┐
      │      │         │
      ▼      ▼         ▼
 Transaction Notification Analytics
 Service     Service     Service
```

---

# 23. Ports

I'd standardize them like this:

| Component            |                       Port |
| -------------------- | -------------------------: |
| API Gateway          |                     `8080` |
| Transaction Service  |                     `8081` |
| Fraud Service        |                     `8082` |
| Notification Service |                     `8083` |
| Analytics Service    |                     `8084` |
| Kafka                |                     `9092` |
| Kafka UI             |                     `8085` |
| PostgreSQL           |                     `5432` |
| Pinot                | `9000` / other Pinot ports |
| Novu                 |                      later |

But don't expose every internal service publicly in production.

Eventually:

```text
Internet
   │
   ▼
Gateway :8080
   │
   ├── Transaction Service :8081
   ├── Analytics Service   :8084
   └── ...
```

The internal services can communicate over the private Docker/network layer.

---

# 24. Databases

I would **not give every service its own PostgreSQL database** for this particular project.

Your primary database belongs to:

```text
Transaction Service
        │
        ▼
    PostgreSQL
```

Fraud decisions could initially be persisted in the transaction service or a small fraud schema/table depending on your design.

Pinot is your separate analytical store:

```text
Kafka
  │
  ▼
Pinot
```

Novu owns notification-related persistence.

This keeps the project manageable.

---

# 25. The final list you can put into your design document

## Applications

```text
1. API Gateway
2. Transaction Service
3. Fraud Service
4. Notification Service
5. Analytics Service
```

## Infrastructure

```text
6. PostgreSQL
7. Apache Kafka
8. Kafka UI
9. Apache Pinot
10. Novu
```

## Kafka producers

```text
1. TransactionEventProducer
2. FraudEventProducer
```

## Kafka consumers

```text
1. FraudTransactionConsumer
2. TransactionFraudDecisionConsumer
3. NotificationTransactionConsumer
4. NotificationFraudConsumer
5. AnalyticsTransactionConsumer
6. AnalyticsFraudConsumer
```

## Topics

### Core

```text
transaction.events
fraud.events
```

### Failure handling

```text
transaction.events.DLT
fraud.events.DLT
notification.events.DLT
```

### Optional later

```text
transaction.events.retry
fraud.events.retry
notification.events.retry
```

## Partitions

Start with:

```text
transaction.events → 3 partitions
fraud.events       → 3 partitions
```

And use:

```text
accountId
```

as the key.

## Consumer groups

```text
fraud-service-group
notification-service-group
analytics-service-group
transaction-service-group
```

The last one consumes `fraud.events`.

---

# 26. And this is the Kafka learning path I'd follow

Don't build all five services immediately.

That's the trap.

Build them in this order:

```text
PHASE 1
Transaction Service
       │
       ▼
PostgreSQL
```

Then:

```text
PHASE 2
Transaction Service
       │
       ▼
Kafka
       │
       ▼
Simple Consumer
```

Then learn:

```text
Topic
  ↓
Partition
  ↓
Record
  ↓
Key
  ↓
Offset
  ↓
Consumer
  ↓
Consumer Group
```

Then:

```text
PHASE 3
Transactional Outbox
```

Then:

```text
PHASE 4

transaction.events
       │
       ├── Fraud Service
       ├── Notification Service
       └── Analytics Service
```

Then:

```text
PHASE 5
Retries
DLT
Idempotency
Failure recovery
```

Then:

```text
PHASE 6
Pinot
Novu
Drools
```

And finally:

```text
PHASE 7
Observability
Docker deployment
Cloud deployment
Load testing
```

