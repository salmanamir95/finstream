# State diagrams

## Account lifecycle

```mermaid
stateDiagram-v2
  [*] --> ACTIVE: proposed create policy
  ACTIVE --> INACTIVE
  ACTIVE --> BLOCKED
  INACTIVE --> ACTIVE
  BLOCKED --> ACTIVE: authorised review
  ACTIVE --> CLOSED
  INACTIVE --> CLOSED
  BLOCKED --> CLOSED
  CLOSED --> [*]
```

All four state names are confirmed in `AccountStatus`; allowed transitions are proposed because the service does not enforce them. Transaction and payment state diagrams are deferred until those domains exist.
