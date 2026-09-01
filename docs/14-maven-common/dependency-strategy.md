# Dependency, version, and configuration strategy

| Technology | Root POM | Common base | Account | Reason |
|---|---|---|---|---|
| Spring Boot | manages baseline via parent | no server dependency by default | Web/Data/Validation/Actuator as used | Services opt into capabilities. |
| PostgreSQL | manages version | No | runtime | Account owns persistence. |
| MapStruct/Lombok | manages versions + processors | optional only if shared models need it | Yes | Compile-time mapping/service model use. |
| OpenAPI | manages version | No | Yes | Account owns its public contract. |
| Micrometer/OpenTelemetry | manages compatible versions | conventions/optional starter | Actuator + chosen exporter | Runtime instrumentation remains service/infrastructure aware. |
| Resilience | manages version | no policy by default | only remote-call services | Timeouts/retries are operation-specific. |
| Testing | manages test baseline | tests its own library | tests its behaviour | Never make test framework a production transitive dependency. |

Version ownership is root/BOM responsibility; dependency usage is each module’s responsibility. For the current monorepo, use one parent and reactor version. Later, publish a FinStream BOM plus a small Common artifact to an internal Maven repository, use semantic versions, and allow services to version independently. `-SNAPSHOT` is mutable development output and should not be used as a production release. Releases such as 1.0.0/1.1.0/2.0.0 should communicate compatible/additive/breaking contracts.

Service configuration belongs under the service’s `application.yaml` and profile-specific files (`application-dev.yaml`, `application-prod.yaml`). Common may provide configuration-property classes or opt-in defaults, but must not ship a generic `application.yaml` that overrides consumer behaviour. Share configuration conventions—names, validation, secret sourcing—not service-specific URLs, credentials, ports, or datasource settings.
