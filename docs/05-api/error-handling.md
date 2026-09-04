# Error handling

## Current — confirmed

Current code throws `RuntimeException` for not-found and lets framework/database errors escape. There is no visible `@ControllerAdvice`, so exact error response shape and status are not a stable documented contract. Do not build client logic around the proposed envelope below.

## Proposed error envelope

```json
{"timestamp":"2026-09-01T12:00:00Z","status":400,"error":"VALIDATION_ERROR","message":"Invalid request","path":"/api/v1/accounts/current","traceId":"…"}
```

Add `@ControllerAdvice` with typed `AccountNotFoundException` (404), `DuplicateAccountNumberException` (409), `BusinessRuleViolationException` (422), validation exceptions (400), access denied (403), authentication required (401), and a sanitised 500 response. Never return stack traces or database details. Log a trace ID internally.
