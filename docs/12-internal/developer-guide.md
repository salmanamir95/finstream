# Developer guide

Prerequisites: Java 21, Maven wrapper, and PostgreSQL. Configure a database matching `account/src/main/resources/application.yaml` or override datasource environment/properties; do not reuse the committed credentials outside local development.

Run: `cd account && ./mvnw spring-boot:run`. Test: `./mvnw test`. Package: `./mvnw package`. MapStruct implementations are generated during compilation under `target/generated-sources/annotations`; inspect them when mapping behaviour is surprising. Actuator health is at `/actuator/health` when running.

Structure: `controller` HTTP, `service` use cases, `domain` JPA, `repository` persistence, `mappers` conversions, and `inputsAndDTOs` boundary models. Keep schema changes in Liquibase changesets.
