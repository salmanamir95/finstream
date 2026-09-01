# Error reference

Exact error responses are not yet a stable contract. Expected HTTP meanings for the future contract: 400 invalid request; 401 authentication required; 403 access denied; 404 account not found; 409 duplicate/conflict; 429 retry later; 500 server error. Clients should log a safe correlation ID when supplied, correct 4xx requests, use bounded retry for transient 5xx/429 responses, and contact support for persistent failures.
