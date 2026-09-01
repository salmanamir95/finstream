# Recommended test cases

| ID | Scenario | Expected result |
|---|---|---|
| TEST-001 | Create each subtype with valid request | Persisted subtype and response contract. |
| TEST-002 | Increase overdraft by positive amount | Increment persists atomically. |
| TEST-003 | Replace rate with negative amount | Stable 400/422 response. |
| TEST-004 | Duplicate account number | Stable 409; no duplicate row. |
| TEST-005 | Missing/invalid fields | Validation response, no write. |
| TEST-006 | Missing subtype ID | Stable 404. |
| TEST-007 | Concurrent update | One stale update rejected once versioning added. |
| TEST-008 | Unauthorised foreign account access | 403/404 policy result once security exists. |
