# C4, dependency, and DFD diagrams

## C4 Level 1 — context

```mermaid
flowchart LR
  User[Client / operator] --> FS[FinStream platform]
  FS --> Account[Account service\ncurrent]
  Account --> DB[(Account PostgreSQL)]
  Future[Customer, Transaction, Payment, Notification\nproposed]:::future
  FS -. future capabilities .-> Future
  classDef future stroke-dasharray:5 5,fill:#fff7e6
```

## C4 Level 2 / dependency direction

```mermaid
flowchart TB
  Root[FinStream parent + aggregator] --> Common[Common library]
  Root --> Account[Account service]
  Account --> Common
  Customer[Customer service\nfuture] --> Common
  Tx[Transaction service\nfuture] --> Common
  Account -. forbidden .-> Customer
  Account -. forbidden DB access .-> Customer
```

The Common edge is a build-time dependency; it is not an inter-service runtime call.

## C4 Level 3 — Account using Common

```mermaid
flowchart LR
  Ctrl[Account controller] --> UseCase[Account service/use case]
  UseCase --> Repo[Account repository]
  Repo --> DB[(Account DB)]
  Ctrl -. common error/correlation convention .-> Common[Common JAR]
```

## DFD Level 0–3

```mermaid
flowchart LR
  Client -->|account request| Account[Account process]
  Account <--> Store[(Account data store)]
  Account -->|response| Client
```

Level 1 separates Account (current) from Customer/Transaction/Payment/Notification (proposed). Level 2 Account flow is client → controller → validation/use case → repository → Account data store → response. Level 3 create account is validate request → authorise customer access (proposed) → apply account rules → persist local transaction → map result → audit/outbox event (proposed) → response. Future transfer flow must coordinate Transaction and Ledger through local commits plus Saga/outbox, not a cross-service database transaction.
