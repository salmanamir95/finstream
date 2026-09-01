# Client acceptance criteria

- Given a valid customer identifier and unique account number, when a current/savings create request is accepted, then the matching account record is returned.
- Given an existing matching account ID, when it is retrieved, then its returned type-specific details are available.
- Given valid updated shared fields, when an update is accepted, then subsequent retrieval reflects them.
- Given an existing matching account, when deleted, then the delete response is successful and later retrieval fails.
- Given a positive amount, when a current-account overdraft increase is accepted, then the returned limit increases.
- Given a non-negative rate, when a savings rate update is accepted, then the returned rate reflects it.

Authentication, ownership, and consistent validation errors are planned acceptance criteria, not delivered ones.
