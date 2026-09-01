# Security runbook

On suspected credential/token compromise: revoke/rotate affected secret or key, restrict access, preserve logs, assess scope, notify the incident lead, and issue a post-incident review. Do not print secrets in tickets or logs.

For suspicious account activity, preserve request/audit evidence, freeze only through an authorised process, validate affected identities, and involve product/compliance owners. Current service has no audit/freeze implementation; this is an operational target. Test secret rotation and database-access revocation regularly.
