# FinStream Maven and Common Library Architecture

This is the authoritative build-architecture supplement for FinStream, assessed from the root, `common`, and `account` POMs on 1 September 2026. Labels distinguish **current** code from the **recommended** target.

| Current finding | Result |
|---|---|
| Parent chain | Corrected to `finstream` → `common` → `account`, all at `0.0.1-SNAPSHOT`. |
| Common parent | `common` is now a `pom`-packaged intermediate parent. |
| Shared library | `common-core` is a dependency-light JAR module; Account depends on it. |
| Build validation | `mvn validate` succeeds with reactor order Root → Common → Common Core → Account. |

Maven parent projects must have `pom` packaging. Therefore `common` cannot also be the shared JAR; `common-core` cleanly separates inheritance from reusable code.

Start with [Maven hierarchy](maven-hierarchy.md), [Common-library boundaries](common-library.md), and [developer guide](developer-guide.md).
