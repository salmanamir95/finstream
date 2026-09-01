# Troubleshooting

| Symptom | Checks |
|---|---|
| Bean/mapper missing | Compile with Maven; confirm MapStruct processor and generated sources. |
| Database connection failure | Check PostgreSQL process, URL/port/database/user, network, and secret source. |
| Port in use | Change `server.port` or stop the local process using 8080. |
| Migration failure | Inspect Liquibase changelog/checksum and database state; do not manually edit applied changesets. |
| Unexpected schema change | Ensure Hibernate schema update is disabled outside development. |
| 500 for expected client error | Add/verify typed exception handler and validation. |
