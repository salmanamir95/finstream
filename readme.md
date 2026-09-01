# FinStream — Event-Driven FinTech Transaction Platform

> A production-oriented FinTech backend built with **Java, Spring Boot, Apache Kafka, PostgreSQL, Drools, Novu, and Apache Pinot** to demonstrate event-driven architecture, reliable financial transaction processing, real-time fraud detection, notifications, and streaming analytics.

---

## 📌 Overview

**FinStream** is a small event-driven financial transaction platform designed around **Apache Kafka**.

The system allows users to perform financial transfers while asynchronously processing transaction events through independent downstream capabilities:

* **Fraud detection** using Apache Drools
* **Notifications** using Novu
* **Real-time analytics** using Apache Pinot

The primary goal of the project is not to build a complete banking application. Instead, it is designed to demonstrate how Kafka can be used as the backbone of a **reliable, scalable, event-driven financial system**.

The project focuses heavily on:

* Kafka producers and consumers
* Topics and partitions
* Consumer groups
* Event-driven communication
* Event ordering
* Consumer offsets
* Idempotent processing
* Retries and Dead Letter Topics
* Transactional Outbox
* Eventual consistency
* Fault tolerance
* Real-time stream processing
* Observability
* Production deployment

---

# 🏗️ Architecture

```text
                              ┌──────────────────────┐
                              │      Client / UI     │
                              └──────────┬───────────┘
                                         │
                                         │ REST
                                         ▼
                              ┌──────────────────────┐
                              │   FinStream API      │
                              │                      │
                              │   Java + Spring Boot │
                              └──────────┬───────────┘
                                         │
                          ┌──────────────┴──────────────┐
                          │                             │
                          ▼                             ▼
                 ┌─────────────────┐          ┌─────────────────┐
                 │   PostgreSQL    │          │  Transaction    │
                 │                 │          │     Outbox      │
                 │ Accounts        │          └────────┬────────┘
                 │ Transactions    │                   │
                 │ Users           │                   │
                 └─────────────────┘                   │
                                                       ▼
                                            ┌────────────────────┐
                                            │       KAFKA        │
                                            │                    │
                                            │ transaction.events │
                                            └─────────┬──────────┘
                                                      │
                            ┌─────────────────────────┼─────────────────────────┐
                            │                         │                         │
                            ▼                         ▼                         ▼
                    ┌───────────────┐        ┌────────────────┐        ┌──────────────┐
                    │    DROOLS     │        │      NOVU      │        │    PINOT     │
                    │               │        │                │        │              │
                    │ Fraud / Risk  │        │ Notifications  │        │  Analytics   │
                    │   Detection   │        │                │        │              │
                    └───────┬───────┘        └────────────────┘        └──────────────┘
                            │
                            │
                            ▼
                    ┌────────────────┐
                    │  fraud.events  │
                    └───────┬────────┘
                            │
                            ▼
                    ┌────────────────────┐
                    │   FinStream API    │
                    │                    │
                    │ Approve / Block    │
                    │ Transaction        │
                    └────────────────────┘
```

---

# 🎯 Project Goals

The project has two major goals.

## 1. Build a realistic FinTech backend

The system supports:

* User accounts
* Account balances
* Money transfers
* Transaction history
* Transaction status
* Fraud detection
* Fraud alerts
* Notifications
* Real-time transaction analytics

## 2. Learn Apache Kafka deeply

Kafka is not included merely as a message queue.

The project intentionally demonstrates:

* Producers
* Consumers
* Topics
* Partitions
* Message keys
* Consumer groups
* Offsets
* Consumer lag
* Ordering
* Retries
* Dead Letter Topics
* Idempotency
* Event-driven workflows
* Transactional Outbox
* Eventual consistency
* Failure recovery

---

# 🧩 Core Features

## 1. Account Management

Users can create and manage financial accounts.

Example:

```text
User
 └── Account
      ├── accountId
      ├── userId
      ├── currency
      └── balance
```

---

## 2. Money Transfer

A user can transfer money between accounts.

Example:

```http
POST /api/v1/transfers
```

```json
{
  "fromAccountId": "ACC-1001",
  "toAccountId": "ACC-2001",
  "amount": 5000.00,
  "currency": "USD"
}
```

The transaction flow is:

```text
Request
   │
   ▼
Validate
   │
   ▼
Check balance
   │
   ▼
Debit sender
   │
   ▼
Credit receiver
   │
   ▼
Create transaction
   │
   ▼
Write Outbox Event
   │
   ▼
Commit PostgreSQL transaction
```

The event is subsequently published to Kafka.

---

# 3. Transaction Events

A successful transaction produces an event.

Example:

```json
{
  "eventId": "evt-123",
  "eventType": "TRANSACTION_CREATED",
  "transactionId": "txn-123",
  "accountId": "ACC-1001",
  "fromAccountId": "ACC-1001",
  "toAccountId": "ACC-2001",
  "amount": 5000.00,
  "currency": "USD",
  "timestamp": "2026-08-29T09:30:00Z"
}
```

The event is published to:

```text
transaction.events
```

---

# 4. Real-Time Fraud Detection

Transaction events are consumed by the fraud-processing component.

The fraud engine uses **Apache Drools** to evaluate transaction rules.

Example rules:

```text
Transaction amount > configured threshold
        ↓
HIGH RISK
```

```text
More than N transactions within X seconds
        ↓
SUSPICIOUS
```

```text
Unusual transaction pattern
        ↓
SUSPICIOUS
```

A fraud decision produces another Kafka event:

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
  "reason": "HIGH_TRANSACTION_VELOCITY",
  "timestamp": "2026-08-29T09:30:02Z"
}
```

The transaction workflow can then react to the fraud decision.

---

# 5. Notifications

Transaction and fraud events can trigger notifications.

The system integrates with **Novu** for notification delivery.

Example:

```text
TransactionCreated
       │
       ▼
Kafka
       │
       ▼
Notification Consumer
       │
       ▼
Novu
       │
       ├── Email
       ├── Push
       └── In-App
```

Example notification:

```text
Your transfer of $5,000 was completed successfully.
```

Fraud example:

```text
A suspicious transaction was detected on your account.
```

---

# 6. Real-Time Analytics

Transaction events are streamed into **Apache Pinot**.

Pinot provides low-latency analytical queries over the event stream.

The analytics layer can expose metrics such as:

```text
Transactions per minute
Total transaction volume
Average transaction amount
Failed transactions
Blocked transactions
Fraud rate
Transactions by currency
Transactions by account
Transaction volume over time
```

Example:

```sql
SELECT
    COUNT(*) AS transaction_count,
    SUM(amount) AS transaction_volume
FROM transactions
WHERE timestamp > ago('PT5M');
```

---

# 📨 Kafka Architecture

Kafka acts as the central event backbone.

## Topics

The initial topic structure is intentionally small.

```text
transaction.events
fraud.events
notification.events

transaction.events.retry
notification.events.retry

notification.events.DLT
```

Additional topics may be introduced as the project evolves.

---

# 🔑 Kafka Message Keys

Transaction events will use a deterministic key such as:

```text
accountId
```

This allows transactions belonging to the same account to be routed consistently to the same partition.

Conceptually:

```text
account-A
    ↓
Partition 0

account-B
    ↓
Partition 1

account-C
    ↓
Partition 2
```

This allows the system to preserve ordering for events belonging to the same key while still allowing the topic to scale horizontally.

---

# 📦 Partitions

The main transaction topic will use multiple partitions.

Example:

```text
transaction.events

Partition 0
Partition 1
Partition 2
```

The project will demonstrate:

* Partition assignment
* Message keys
* Ordering
* Parallel consumption
* Consumer scaling

---

# 👥 Consumer Groups

Different capabilities consume the same transaction stream independently.

```text
transaction.events
        │
        ├──────────────► fraud-group
        │
        ├──────────────► notification-group
        │
        └──────────────► analytics-group
```

Each consumer group maintains its own offsets.

Therefore, fraud processing can consume an event independently from notification processing.

---

# 🔄 Event Flow

## Successful transaction

```text
Client
  │
  ▼
Spring Boot API
  │
  ▼
PostgreSQL
  │
  ▼
Outbox
  │
  ▼
Kafka
  │
  ├─────────────► Fraud Processing
  │
  ├─────────────► Notification Processing
  │
  └─────────────► Pinot Analytics
```

---

# 🚨 Fraud Event Flow

```text
Transaction
     │
     ▼
Kafka
     │
     ▼
Fraud Consumer
     │
     ▼
Drools
     │
     ├── APPROVED
     │
     └── BLOCKED
             │
             ▼
        fraud.events
             │
             ├──────────► Transaction Service
             │
             └──────────► Notification
```

---

# 🔁 Retry & Dead Letter Processing

External systems can fail.

For example:

```text
Kafka
  │
  ▼
Notification Consumer
  │
  ▼
Novu
  X
  │
  ▼
Retry
  │
  X
  │
  ▼
Retry
  │
  X
  │
  ▼
notification.events.DLT
```

The Dead Letter Topic allows failed messages to be inspected and replayed later.

The project will demonstrate:

* Retry policies
* Backoff
* Poison messages
* Dead Letter Topics
* Error handling
* Manual replay

---

# 🔐 Idempotency

Financial events must not be processed twice accidentally.

Every important event contains a unique:

```text
eventId
```

and/or:

```text
transactionId
```

Consumers maintain an idempotency strategy so that:

```text
TX-123
```

processed twice does not result in:

```text
TX-123
TX-123
```

being applied twice.

Conceptually:

```text
Receive event
     │
     ▼
Check eventId
     │
     ├── Already processed ──► Ignore
     │
     └── New event
            │
            ▼
        Process event
            │
            ▼
      Mark as processed
```

---

# 💾 Transactional Outbox Pattern

The system uses the **Transactional Outbox Pattern** to avoid the classic dual-write problem.

Without an outbox:

```text
PostgreSQL
     │
     ├── Save transaction ── SUCCESS
     │
     └── Publish Kafka ───── FAILURE
```

Now the database contains a transaction that was never published to Kafka.

The outbox solves this:

```text
                    PostgreSQL
                        │
                Single DB Transaction
                        │
                ┌───────┴────────┐
                │                │
                ▼                ▼
          transactions        outbox
                                  │
                                  ▼
                           Outbox Publisher
                                  │
                                  ▼
                                Kafka
```

The database transaction and outbox insertion succeed or fail together.

A separate publisher then sends the outbox event to Kafka.

---

# 📊 Analytics Architecture

```text
                    Kafka
                      │
                      ▼
                Apache Pinot
                      │
                      ▼
                    SQL
                      │
                      ▼
                Analytics API
                      │
                      ▼
                 Dashboard
```

Possible dashboard:

```text
┌──────────────────────────────────────────────┐
│              FINSTREAM ANALYTICS             │
├──────────────────────────────────────────────┤
│                                              │
│ Transactions        18,421                   │
│ Volume              $4,921,000               │
│ Fraud Rate          1.72%                    │
│ Failed Transfers    142                      │
│                                              │
├──────────────────────────────────────────────┤
│                                              │
│ Transaction Volume Over Time                 │
│                                              │
│       ╭────╮                                 │
│   ╭───╯    ╰──╮                              │
│ ──╯           ╰────────                      │
│                                              │
├──────────────────────────────────────────────┤
│ Fraud Events                                 │
│                                              │
│ High Amount           32                     │
│ High Velocity         21                     │
│ Suspicious Pattern    14                     │
│                                              │
└──────────────────────────────────────────────┘
```

---

# 🛠️ Technology Stack

## Backend

* Java 21+
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Kafka
* Spring Validation
* Maven

## Database

* PostgreSQL

## Event Streaming

* Apache Kafka
* Kafka Admin API
* Kafka Producer API
* Kafka Consumer API

## Fraud Detection

* Apache Drools

## Notifications

* Novu

## Real-Time Analytics

* Apache Pinot

## Infrastructure

* Docker
* Docker Compose

## Testing

* JUnit 5
* Mockito
* Spring Boot Test
* Testcontainers

## Observability

Potential production stack:

* Micrometer
* Prometheus
* Grafana

## Deployment

Target deployment:

* Docker containers
* Cloud VM/container platform
* Managed Kafka or self-hosted Kafka
* Managed PostgreSQL or PostgreSQL instance

---

# 📁 Project Structure

The project will initially follow a modular Spring Boot structure.

```text
finstream/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/finstream/
│   │   │       │
│   │   │       ├── account/
│   │   │       │   ├── controller/
│   │   │       │   ├── service/
│   │   │       │   ├── repository/
│   │   │       │   └── entity/
│   │   │       │
│   │   │       ├── transaction/
│   │   │       │   ├── controller/
│   │   │       │   ├── service/
│   │   │       │   ├── repository/
│   │   │       │   └── entity/
│   │   │       │
│   │   │       ├── kafka/
│   │   │       │   ├── producer/
│   │   │       │   ├── consumer/
│   │   │       │   ├── config/
│   │   │       │   └── event/
│   │   │       │
│   │   │       ├── outbox/
│   │   │       │   ├── entity/
│   │   │       │   ├── repository/
│   │   │       │   └── publisher/
│   │   │       │
│   │   │       ├── fraud/
│   │   │       │   └── drools/
│   │   │       │
│   │   │       ├── notification/
│   │   │       │   └── novu/
│   │   │       │
│   │   │       └── analytics/
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       └── rules/
│   │
│   └── test/
│
├── docker/
│   ├── kafka/
│   ├── postgres/
│   ├── pinot/
│   └── novu/
│
├── docker-compose.yml
│
├── docs/
│   ├── architecture/
│   ├── kafka/
│   └── api/
│
├── pom.xml
└── README.md
```

The exact structure may evolve as the project moves toward production deployment.

---

# 🚀 Getting Started

## Prerequisites

Install:

* Java 21+
* Maven 3.9+
* Docker
* Docker Compose
* Git

Verify:

```bash
java -version
mvn -version
docker --version
docker compose version
```

---

# 🐳 Running Infrastructure

Start the development infrastructure:

```bash
docker compose up -d
```

Check running containers:

```bash
docker compose ps
```

Expected infrastructure includes:

```text
Kafka
PostgreSQL
Pinot
Novu
```

The exact services may change as the project evolves.

---

# ▶️ Running the Application

Start the Spring Boot application:

```bash
./mvnw spring-boot:run
```

Or:

```bash
mvn spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

# 🔌 API

## Create Account

```http
POST /api/v1/accounts
```

Example:

```json
{
  "userId": "USER-001",
  "currency": "USD"
}
```

---

## Transfer Money

```http
POST /api/v1/transfers
```

Example:

```json
{
  "fromAccountId": "ACC-001",
  "toAccountId": "ACC-002",
  "amount": 5000.00,
  "currency": "USD"
}
```

---

## Get Transaction

```http
GET /api/v1/transactions/{transactionId}
```

---

## Get Account Transactions

```http
GET /api/v1/accounts/{accountId}/transactions
```

---

## Get Notifications

```http
GET /api/v1/accounts/{accountId}/notifications
```

---

# 🧪 Testing

Run all tests:

```bash
./mvnw test
```

The project will include:

### Unit Tests

* Transaction service
* Account service
* Fraud rules
* Event serialization
* Idempotency logic

### Integration Tests

* PostgreSQL
* Kafka
* Outbox publisher
* Kafka consumers

### Testcontainers

Kafka and PostgreSQL can be started automatically during integration testing.

Example:

```text
Test
 │
 ├── PostgreSQL container
 │
 ├── Kafka container
 │
 └── Spring Boot application
```

This allows the application to be tested against real infrastructure rather than mocked Kafka behavior.

---

# 🔍 Observability

The production version will expose application and Kafka-related metrics.

Important metrics include:

```text
transaction.count
transaction.failed
transaction.blocked
fraud.events
notification.success
notification.failed

kafka.consumer.lag
kafka.consumer.records
kafka.producer.errors

outbox.pending
outbox.failed
```

The intended observability stack is:

```text
Spring Boot
     │
     ▼
Micrometer
     │
     ▼
Prometheus
     │
     ▼
Grafana
```

---

# 🧯 Failure Scenarios

The project intentionally tests failures instead of assuming everything works.

Examples:

## Kafka unavailable

```text
Application
     │
     ▼
Kafka
     X
```

The transaction should remain safely persisted and the event should remain available through the outbox.

---

## Notification provider unavailable

```text
Kafka
  │
  ▼
Notification Consumer
  │
  ▼
Novu
  X
```

The event should be retried.

After retry exhaustion:

```text
notification.events.DLT
```

---

## Fraud processor crashes

```text
Kafka
  │
  ▼
Fraud Consumer
  X
```

After restart, the consumer resumes from its committed offset.

---

## Duplicate event

```text
eventId = evt-123
```

received twice:

```text
First event
    ↓
Process
    ↓
Mark processed

Second event
    ↓
Already processed
    ↓
Ignore
```

---

# 🔐 Security

The development environment may use simplified credentials.

The production deployment should include:

* HTTPS
* TLS for Kafka
* Authentication
* Authorization
* Secret management
* Database credentials stored outside source control
* Kafka ACLs
* Environment-specific configuration
* Secure API authentication

No secrets should be committed to Git.

Example:

```text
.env
application-local.yml
```

must not contain production credentials.

---

# ☁️ Production Deployment

The final version is intended to run as containerized services.

Conceptually:

```text
                         Internet
                            │
                            ▼
                     Load Balancer
                            │
                            ▼
                    Spring Boot API
                            │
                 ┌──────────┴──────────┐
                 ▼                     ▼
             PostgreSQL              Kafka
                                       │
                    ┌──────────────────┼──────────────────┐
                    ▼                  ▼                  ▼
                  Drools              Novu               Pinot
```

The deployment should include:

* Containerized application
* Production PostgreSQL
* Kafka cluster
* TLS
* Secrets
* Health checks
* Metrics
* Centralized logs
* Automated deployment

Kubernetes is intentionally **not required for the first production deployment**.

The project should first demonstrate that the system can be reliably deployed using simpler container infrastructure.

---

# 📈 Scalability

Kafka allows the processing layer to scale horizontally.

For example:

```text
transaction.events
│
├── Partition 0
├── Partition 1
└── Partition 2
```

Multiple fraud consumers can process partitions concurrently:

```text
fraud-group

Consumer 1 → Partition 0
Consumer 2 → Partition 1
Consumer 3 → Partition 2
```

If traffic increases, partitions and consumers can be scaled appropriately.

---

# 📚 Kafka Concepts Demonstrated

This project intentionally covers the following Kafka concepts.

## Fundamentals

* Kafka brokers
* Kafka cluster
* Topics
* Partitions
* Records
* Keys
* Offsets
* Producers
* Consumers

## Consumer Model

* Consumer groups
* Partition assignment
* Rebalancing
* Consumer lag
* Offset commits
* Offset management

## Reliability

* At-least-once processing
* Idempotency
* Retries
* Dead Letter Topics
* Error handling
* Event replay

## Data Consistency

* Transactional Outbox
* Eventual consistency
* Database/Kafka dual-write problem

## Performance

* Partitioning
* Parallel consumers
* Batching
* Compression
* Consumer throughput

## Production Kafka

* Replication
* ISR
* Leader/follower architecture
* KRaft
* Security
* Monitoring
* Failure recovery

---

# 🧠 What This Project Is Designed to Teach

By completing this project, the goal is to be able to explain:

### Why Kafka?

Because multiple systems need to react to financial events independently without tightly coupling the transaction API to every downstream system.

### Why partitions?

To allow Kafka topics to scale while maintaining ordering for related events.

### Why consumer groups?

To allow multiple instances of a service to process partitions in parallel.

### Why the Outbox Pattern?

To prevent database and Kafka from getting out of sync because of a dual-write failure.

### Why idempotency?

Because at-least-once delivery can result in duplicate processing, which is unacceptable for financial operations.

### Why Dead Letter Topics?

Because some events cannot be successfully processed and need to be isolated without stopping the entire consumer pipeline.

### Why event-driven architecture?

Because fraud detection, notifications, analytics, and other capabilities can evolve independently from the transaction system.

---

# 🗺️ Development Roadmap

## Phase 1 — Core FinTech API

* [ ] Create Spring Boot project
* [ ] Configure PostgreSQL
* [ ] Implement User
* [ ] Implement Account
* [ ] Implement Transaction
* [ ] Implement money transfer
* [ ] Add validation
* [ ] Add transaction history
* [ ] Add unit tests

---

## Phase 2 — Kafka Fundamentals

* [ ] Run Kafka locally
* [ ] Create `transaction.events`
* [ ] Implement Kafka producer
* [ ] Publish transaction events
* [ ] Implement Kafka consumer
* [ ] Experiment with partitions
* [ ] Experiment with message keys
* [ ] Create consumer groups
* [ ] Understand offsets
* [ ] Monitor consumer lag

---

## Phase 3 — Transactional Outbox

* [ ] Create outbox table
* [ ] Write transaction + outbox record atomically
* [ ] Implement outbox publisher
* [ ] Publish events to Kafka
* [ ] Implement retry
* [ ] Handle failed publishing
* [ ] Test database/Kafka failure scenarios

---

## Phase 4 — Fraud Detection

* [ ] Integrate Apache Drools
* [ ] Create basic fraud rules
* [ ] Consume transaction events
* [ ] Generate fraud decisions
* [ ] Publish `fraud.events`
* [ ] Implement fraud notification flow
* [ ] Test temporal/velocity rules

---

## Phase 5 — Notifications

* [ ] Integrate Novu
* [ ] Create notification workflows
* [ ] Consume relevant Kafka events
* [ ] Implement idempotency
* [ ] Implement retries
* [ ] Implement DLT
* [ ] Test notification failures

---

## Phase 6 — Real-Time Analytics

* [ ] Integrate Apache Pinot
* [ ] Stream transaction events into Pinot
* [ ] Create analytical tables
* [ ] Write analytical queries
* [ ] Build transaction metrics
* [ ] Build fraud metrics
* [ ] Build real-time dashboard

---

## Phase 7 — Production Hardening

* [ ] Add authentication
* [ ] Add authorization
* [ ] Add Kafka security
* [ ] Add TLS
* [ ] Add health checks
* [ ] Add structured logging
* [ ] Add metrics
* [ ] Add Prometheus
* [ ] Add Grafana
* [ ] Add integration tests
* [ ] Add failure tests

---

## Phase 8 — Deployment

* [ ] Containerize application
* [ ] Create production Docker configuration
* [ ] Deploy PostgreSQL
* [ ] Deploy Kafka
* [ ] Deploy application
* [ ] Deploy Pinot
* [ ] Configure Novu
* [ ] Configure secrets
* [ ] Configure TLS
* [ ] Configure monitoring
* [ ] Configure CI/CD
* [ ] Perform production load testing

---

# 🚀 Future Improvements

Possible future extensions include:

* Apache Flink for advanced stream processing
* More sophisticated fraud detection
* Event sourcing
* Schema Registry
* Avro/Protobuf
* Kafka Streams
* Multi-region Kafka
* Kafka MirrorMaker 2
* Kubernetes
* Distributed tracing
* OpenTelemetry
* Advanced rate limiting
* Real-time risk scoring
* Machine-learning fraud models

These are deliberately **not part of the initial scope**.

The goal is to master the core architecture before adding more infrastructure.

---

# 🎓 Learning Outcomes

After completing FinStream, you should be comfortable with:

```text
Java
Spring Boot
REST APIs
PostgreSQL
Docker
Apache Kafka
Kafka Producers
Kafka Consumers
Consumer Groups
Partitions
Offsets
Consumer Lag
Event-driven Architecture
Transactional Outbox
Idempotency
Retries
Dead Letter Topics
Eventual Consistency
Fraud Rules
Real-time Analytics
Distributed Systems
Production Deployment
Observability
```

More importantly, you should be able to reason about **why** each component exists and what happens when individual components fail.

---

# ⚠️ Disclaimer

FinStream is an educational and portfolio project.

It is **not a production banking system** and should not be used to process real customer funds or sensitive financial information.

Real financial systems require substantially more work around:

* Regulatory compliance
* Security
* KYC/AML
* Authentication
* Authorization
* Encryption
* Auditing
* Financial reconciliation
* Disaster recovery
* Regulatory reporting
* Operational controls
* Data privacy
* Fraud prevention
* High availability

The financial transaction logic in this project is intended to demonstrate distributed-system and event-streaming concepts.

---

# 📄 License

This project is intended to be released under the **Apache License 2.0** unless otherwise specified.

Third-party dependencies and integrated open-source projects retain their respective licenses.

---

# 👨‍💻 Author

Built as a hands-on exploration of:

**Java + Spring Boot + Apache Kafka + Event-Driven Architecture + Distributed Systems**

The primary objective is simple:

> **Build a small but realistic FinTech system and use it to understand Apache Kafka deeply.**
