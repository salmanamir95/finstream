# CI/CD

No pipeline is evidenced. Proposed stages: checkout → compile → unit tests → PostgreSQL/Testcontainers integration tests → static analysis → dependency/container scan → package → image build/sign → deploy to staging → migration → smoke/contract tests → approved production rollout → monitor/rollback decision. GitHub Actions, GitLab CI, or Jenkins are viable; select based on organisational tooling, not this document.
