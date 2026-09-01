# OpenAPI contract recommendation

The Springdoc dependency is present, but generated OpenAPI/UI availability was not verified. Define an OpenAPI 3.1 document in source control with tags `Current Accounts` and `Savings Accounts`; schemas `CreateAccountRequest`, `UpdateAccountRequest`, `CurrentAccountResponse`, `SavingsAccountResponse`, and `Problem`; and a bearer OAuth2 security scheme when security is added.

Every path should declare success, validation, unauthenticated, forbidden, not-found, conflict, and server-error responses. Keep API examples in the contract and run contract validation in CI. The current API must be corrected before its specification is treated as a consumer guarantee.
