# Entity model and inheritance

`@Inheritance(strategy = JOINED)` stores shared fields in `account` and subtype fields in child tables. It normalises subtype data and preserves database-level subtype structure, at the cost of joins and more complex polymorphic queries.

| Strategy | Benefits | Costs | Fit |
|---|---|---|---|
| JOINED (current) | Normalised, subtype constraints | Joins and insert complexity | Good for modest subtypes with shared identity. |
| SINGLE_TABLE | Fast/simple polymorphic reads | Many nullable columns; weaker subtype constraints | Good for few stable fields. |
| TABLE_PER_CLASS | Separate tables | Poor polymorphic querying/duplicated columns | Rarely preferred. |

The mapping is appropriate for learning and potentially production, but validate it against real query patterns. The matching Liquibase child FKs support JOINED.
