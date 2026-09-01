# Platform architecture overview

FinStream is organised as a platform with independently focused capabilities. A shared platform library gives each capability the same basic conventions for errors, logging, request tracking, and security integration, while each service keeps ownership of its own business rules and data.

This separation lets a future account, customer, transaction, payment, or notification capability evolve without one team changing another team’s internal records. It improves maintainability and enables scaling services according to real demand. It does not mean every capability already exists: today, the Account service is the implemented runtime capability.

The platform is designed to standardise operational visibility and security expectations while avoiding a single oversized shared component. Availability targets, support commitments, and production security controls remain subject to delivery and agreement.
