# Common library: purpose and boundaries

`common` is the intermediate parent POM; `common-core` is the **reusable Java library**, not a runtime microservice. Maven compiles Common Core to a JAR; a consuming service declares `com.finstream:common-core`, and Maven places that JAR on the service compile/runtime classpath:

```text
common-core source → compile/package → common-core JAR → Account dependency → Account application
```

It should have no `@SpringBootApplication`, `main()` method, service port, service identity, datasource, or `application.yaml`. Those imply a deployable runtime and can accidentally auto-configure every consumer. A plain JAR needs no application bootstrap.

## Good candidates

| Category | Examples | Boundary |
|---|---|---|
| API/platform models | `ApiError`, `ErrorCode`, pagination envelope, request context | Only stable cross-service semantics. |
| Exceptions | base business/validation/not-found exceptions | Services map their own domain details. |
| Validation | reusable annotation/validator with universal rule | No Account policy encoded. |
| Observability | correlation-ID filter/interceptor, log field constants, telemetry conventions | Prefer opt-in Spring Boot starter for auto-configuration. |
| Security primitives | shared exception types, token-claim helper interfaces | Authorisation decisions stay in owner service. |
| Serialization | shared ObjectMapper modules/standard error format | Version and test contract compatibility. |

## Must not contain

Account/Customer/Transaction entities, repositories, controllers, services, business workflows, account statuses that have no cross-service meaning, database migrations, or service configuration must stay in their owning service. Do not share JPA entities/repositories: doing so couples schemas and lets one service silently own another’s data.

The rule is: **share infrastructure and carefully governed contracts; do not casually share business logic.** Shared business code creates a distributed monolith: one library release can change multiple services’ behaviour, force lockstep deployments, and hide ownership. If a capability is reused but has domain meaning, expose it as a versioned API/event or give it a dedicated bounded context instead.

## Module shape

`common` is deliberately `pom` packaged so it can be the parent of services. `common-core` is deliberately small and dependency-light. If Spring auto-configuration becomes justified, add `finstream-common-spring-boot-starter` rather than making every consumer inherit Web/JPA/OpenAPI. Keep data access, HTTP server dependencies, and database drivers out of Common Core. Add dependencies only after at least two services have a stable, identical need.
