# Business rules and validation boundaries

Structural validation belongs at the API boundary: `@NotNull customerId`, `@Positive customerId`, `@NotBlank accountNumber`, format/length validation, and `@Valid` on bodies. The current DTOs lack these annotations and controllers do not use `@Valid`.

Business validation belongs in transactional application/domain services: verify customer existence and ownership; choose an initial status; enforce lifecycle transitions; apply configured overdraft and rate ranges; and decide whether close means soft-close rather than delete.

Current confirmed rules: account number must be unique at the database; overdraft-increase amount must be positive; interest-rate replacement cannot be negative. A database uniqueness violation is not currently translated to a stable conflict response.
