# FinStream documentation

This package documents the repository as inspected on 4 September 2026. Its status labels are deliberate:

- **Confirmed** — present in source/configuration.
- **Inferred** — a reasonable conclusion from the implementation.
- **Proposed** — a recommendation, not an implemented feature.
- **Unknown** — requires product or operational confirmation.

Start with [Executive summary](00-overview/executive-summary.md), then [Architecture overview](02-architecture/architecture-overview.md) and [Account API](05-api/account-api.md). The current multi-module build and shared-library design is covered in [Maven and Common Library Architecture](14-maven-common/README.md). Client-oriented material is in [`client-docs/`](../client-docs/).

The existing `readme.md` and `docs/microservices.md` describe a broader event-driven target. They are not evidence that those services are implemented; this package records the current repository as the source of truth. In particular, the only implemented HTTP API is the Account service; Person has no controller.
