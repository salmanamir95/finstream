# Logging

Actuator is configured for health/info; structured logging and correlation are not evidenced. Add JSON logs with timestamp, level, service, environment, trace/request ID, operation, outcome, and sanitised error code. Never log credentials, tokens, full personal data, or financial payloads unless policy expressly permits redacted audit capture.
