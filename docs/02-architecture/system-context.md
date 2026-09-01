# System context

**Trust boundary:** an unauthenticated client can currently reach the Account service directly. PostgreSQL credentials are present in configuration. This is acceptable only for local development.

```mermaid
flowchart LR
  U[Client or developer] -->|HTTP, current| F[FinStream Account Service]
  F -->|JDBC, current| P[(Account PostgreSQL database)]
  F -. health/info .-> O[Operator]
  K[(Kafka, current infrastructure only)]:::future
  F -. proposed events .-> K
  I[OIDC identity provider]:::future -. proposed authentication .-> F
  classDef future stroke-dasharray: 5 5,fill:#fff7e6
```

Legend: solid arrows are confirmed interactions; dashed arrows are proposed. There is no external Customer service or identity integration in this repository.
