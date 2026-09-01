# Migration strategy

Liquibase is confirmed through changelogs and configuration. It should be the sole production schema authority. `spring.jpa.hibernate.ddl-auto: update` must be disabled in production (`validate` is an appropriate default) because automatic changes are not reviewed, versioned, or safely rollbackable.

Use immutable, ordered changesets; separate expand, deploy, backfill, and contract phases; test migrations against a production-like copy; and make backup/restore readiness a release gate. Prefer forward-fix migrations over automatic rollback; document any manual rollback and its data consequences.
