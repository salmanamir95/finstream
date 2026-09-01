# Monitoring and health

Current: `/actuator/health` and `/actuator/info` are exposed by configuration. Proposed: liveness checks confirm a running process; readiness checks verify ability to serve traffic; startup checks allow slow initialisation. Build dashboards around client outcomes, database health, and deployment change; define on-call ownership before setting alerts.
