# API reference

| Method | Path | Description |
|---|---|---|
| POST | `/api/accounts/current` | Create current account |
| GET/PUT/DELETE | `/api/accounts/current/{id}` | Read/update/delete current account |
| GET | `/api/accounts/current` | List current accounts |
| GET | `/api/accounts/current/{id}/overdraft-limit` | Read limit |
| PATCH | `/api/accounts/current/{id}/overdraft-limit/increase?amount=…` | Increase limit |
| POST | `/api/accounts/savings` | Create savings account |
| GET/PUT/DELETE | `/api/accounts/savings/{id}` | Read/update/delete savings account |
| GET | `/api/accounts/savings` | List savings accounts |
| GET | `/api/accounts/savings/{id}/interest-rate` | Read rate |
| PATCH | `/api/accounts/savings/{id}/interest-rate?interestRate=…` | Update rate |

Create/update JSON: `{"customerId":1001,"accountNumber":"ACC-10001"}`. Current responses include customer ID, account number, and overdraft limit; savings responses include the interest rate. API versioning/authentication are planned.
