# Scope and constraints

| Area | Status | Notes |
|---|---|---|
| Current and savings account CRUD | Confirmed | Separate endpoints and JPA entities. |
| Overdraft-limit increase/read | Confirmed | Current accounts only. |
| Interest-rate update/read | Confirmed | Savings accounts only. |
| PostgreSQL schema migrations | Confirmed | Liquibase changelog exists. |
| Actuator health/info exposure | Confirmed | Configuration exposes both endpoints. |
| Customer validation/ownership | Not implemented | `customerId` is stored only. |
| Authentication/authorisation | Not implemented | No Spring Security dependency/configuration. |
| Transactions, payments, ledger, notifications | Proposed | No implementations found. |
| Kafka event publication/consumption | Not implemented | Compose defines Kafka; application has no Kafka dependency. |

Assumptions requiring confirmation: who may create/close an account; legal rules for account numbers and interest; supported currencies; data-retention period; availability and recovery objectives; and whether physical deletion is allowed.

Out of scope today: balance management, money movement, reporting, customer lifecycle, and regulatory compliance certification.
