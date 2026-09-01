# Disaster recovery

RPO/RTO are unknown and require business agreement. Proposed baseline: encrypted PostgreSQL backups plus point-in-time recovery, cross-location copy where required, documented restore procedures, and periodic restore drills. Scenarios: bad deployment (roll forward/back compatible release), data corruption (restore to point), database outage (failover/restore), and regional outage (rebuild in alternate region). Event recovery later requires retained topics and consumer replay plans.
