# Testing strategy

Current evidence is a single Spring application-context test. Build a pyramid: fast unit tests for business rules/services; mapper tests for mappings/null policies; repository integration tests against PostgreSQL Testcontainers; controller tests for validation/error/authz; API contract tests; end-to-end tests through deployment; and targeted performance/security tests. Mock network boundaries, not the domain logic under test.
