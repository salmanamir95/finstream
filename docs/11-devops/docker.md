# Docker

`infrastructure/docker-compose.yml` currently provisions Kafka and Kafka UI, with ports 9092 and 8085. It does not provision PostgreSQL, the Account service, or the Person service, and it does not prove Kafka use by the application. The database users and databases in each service configuration must be prepared separately. Add application/PostgreSQL Compose services only with health checks, named volumes, non-default development credentials, and a documented `.env.example` that contains no real secret. Never commit production secrets.
