# Account API — current contract

All routes are **confirmed** from controller mappings. `AccountInput` contains only `customerId` and `accountNumber`; response subtype DTOs expose those inherited fields plus `overdraftLimit` or `interestRate`. The response does not expose `id` or `status`, despite entities containing both.

| ID | Method / path | Purpose | Current result |
|---|---|---|---|
| API-001 | `POST /api/accounts/current` | Create current account | 200 + DTO |
| API-002 | `GET /api/accounts/current/{id}` | Get current account | 200 + DTO |
| API-003 | `GET /api/accounts/current` | List current accounts | 200 + array |
| API-004 | `PUT /api/accounts/current/{id}` | Update shared input fields | 200 + DTO |
| API-005 | `DELETE /api/accounts/current/{id}` | Delete current account | 204 |
| API-006 | `GET /api/accounts/current/{id}/overdraft-limit` | Read limit | 200 + number |
| API-007 | `PATCH /api/accounts/current/{id}/overdraft-limit/increase?amount=` | Increase limit | 200 + DTO |
| API-008 | `POST /api/accounts/savings` | Create savings account | 200 + DTO |
| API-009 | `GET/PUT/DELETE /api/accounts/savings/{id}` | Get/update/delete savings account | 200/200/204 |
| API-010 | `GET /api/accounts/savings` | List savings accounts | 200 + array |
| API-011 | `GET /api/accounts/savings/{id}/interest-rate` | Read rate | 200 + number |
| API-012 | `PATCH /api/accounts/savings/{id}/interest-rate?interestRate=` | Replace rate | 200 + DTO |

Example request (current shape):

```http
POST /api/accounts/current
Content-Type: application/json

{"customerId":1001,"accountNumber":"ACC-10001"}
```

Example response shape (values can be null because the input does not include subtype fields):

```json
{"customerId":1001,"accountNumber":"ACC-10001","overdraftLimit":null}
```

Recommended contract: POST returns `201 Created` with `Location`; list has page/size/sort; IDs/status have clear representation; `409` reports duplicate account number; and protected routes use bearer-token scopes. There is no `/api/accounts` generic CRUD endpoint.
