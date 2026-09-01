# DFD Level 2 — Account operations

```mermaid
flowchart TB
  R[Client request] --> V[HTTP binding\nno structural validation currently]
  V --> S[Specialised service]
  S --> M[Map request to entity]
  M --> P[Repository persists/reads]
  P <--> D[(PostgreSQL)]
  P --> O[Map entity to response DTO]
  O --> R2[HTTP response]
```

Retrieval skips the input-mapping step. Update loads the subtype, maps request fields onto it, then saves. Delete checks existence then deletes. No explicit `@Transactional` boundary is declared; Spring Data repository methods supply individual transactional behaviour, not an explicit business transaction design.
