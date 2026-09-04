# API overview

The FinStream API allows local applications to interact with the account-management capability. The local base URL is `http://localhost:8080`; no production URL or authentication mechanism is provided. JSON is used for request bodies and responses. Account DTO responses are currently wrapped in a `GenericResponse` envelope; specialised limit/rate reads return scalar values. See [API reference](api-reference.md) for actual paths.

The API is unversioned and does not expose account IDs or statuses in its response DTOs. Controller methods do not define explicit HTTP status codes, including deletion, so clients must not assume a `204 No Content` response. See [error reference](error-reference.md) for the current error-contract limitation.
