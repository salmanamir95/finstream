# API quickstart

For local development, start the Account service and ensure PostgreSQL is available. Then create a current account:

The default local database is `finstream_account` on `localhost:5432`, using the development credentials in `account/src/main/resources/application.yaml`. The service applies Liquibase migrations and validates the existing schema at startup.

```bash
curl -X POST http://localhost:8080/api/accounts/current \
  -H 'Content-Type: application/json' \
  -d '{"customerId":1001,"accountNumber":"ACC-10001"}'
```

Authentication/token acquisition is a future production step. Verify the returned details; handle duplicate or server errors safely and do not blindly retry create calls.

Successful DTO responses are wrapped in `GenericResponse`; the current create/update input contains only `customerId` and `accountNumber`. The API currently has no stable error envelope.
