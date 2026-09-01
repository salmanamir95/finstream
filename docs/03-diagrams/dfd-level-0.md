# DFD Level 0

```mermaid
flowchart LR
  Client[Client] -->|account request| FS[FinStream Account capability]
  FS -->|account response| Client
  FS <--> |account data| DB[(Account database)]
```
