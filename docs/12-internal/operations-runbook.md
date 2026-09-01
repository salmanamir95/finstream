# Operations runbook

Start/restart through the approved deployment platform; locally use Maven as in the developer guide. Check `/actuator/health`, recent structured logs, database connectivity/connection pool, and dependency health before declaring recovery. For an incident: classify severity, assign an incident lead, preserve timestamps/trace IDs, mitigate safely, communicate status, then document root cause and follow-ups.

Proposed severity: SEV-1 widespread financial/security impact; SEV-2 major degraded capability; SEV-3 limited workaround; SEV-4 minor issue. Response targets and support roster require confirmation. Roll back only to a schema-compatible release; otherwise use a forward fix.
