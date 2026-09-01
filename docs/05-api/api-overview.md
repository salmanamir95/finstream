# API overview

The implemented API is unversioned and runs on the configured service port (`8080` locally). It accepts JSON request bodies and returns JSON DTOs except for scalar specialised reads. No authentication or authorisation is implemented. Do not expose it publicly.

**Recommendation:** introduce `/api/v1` before external adoption. Version at the contract boundary, make compatible additions within a major version, deprecate with a published window, and avoid breaking field removal/semantic changes.

Use `Create*Request`, `Update*Request`, and `*Response` rather than a shared `AccountInput`/DTO. Entities model persistence; request DTOs define allowed client input; response DTOs define safe output. MapStruct is a compile-time mapper and is appropriate here. For PATCH, use explicit patch fields or JSON Merge Patch with careful null semantics—do not treat null accidentally as an instruction to erase data.
