# Developer guide

## Common commands

From the repository root:

```bash
mvn clean install
mvn -pl common clean install
mvn -pl account -am test
mvn -pl account -am package
```

`-pl` selects a project; `-am` also builds required reactor dependencies. From Account, `mvn spring-boot:run` starts the service; this differs from `mvn clean install`, which builds/tests/installs artifacts but does not run the service. Use `mvn dependency:tree` to diagnose unexpected transitive dependencies and `mvn help:effective-pom` to inspect inherited configuration.

## Add a service

1. Create `service-name/pom.xml` with the root parent/version and a unique artifact ID.
2. Register it in root `<modules>`.
3. Declare only service-used dependencies without versions when root manages them.
4. Add its Spring Boot entry point, service-local configuration, migrations, tests, and documentation.
5. Build from root with `mvn -pl service-name -am test`.

## Add a dependency or shared class

Add a third-party version/property and managed declaration in root; add the dependency without a version only to modules that use it. A Common class requires: stable cross-service meaning, named owner, compatibility policy, tests, and at least two legitimate consumers (unless it is fundamental error/correlation infrastructure). If the class expresses Account rules, put it in Account instead.

Before creating Common auto-configuration, ask whether a tiny base JAR plus an optional Spring Boot starter is safer. Debug parent errors by checking group/artifact/version/relativePath alignment first; the current Account-parent version mismatch is an example.
