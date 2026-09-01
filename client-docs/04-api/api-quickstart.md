# API quickstart

For local development, start the Account service and ensure PostgreSQL is available. Then create a current account:

```bash
curl -X POST http://localhost:8080/api/accounts/current \
  -H 'Content-Type: application/json' \
  -d '{"customerId":1001,"accountNumber":"ACC-10001"}'
```

Authentication/token acquisition is a future production step. Verify the returned details; handle duplicate or server errors safely and do not blindly retry create calls.
