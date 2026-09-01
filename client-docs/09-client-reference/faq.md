# FAQ

**What is available?** Current/savings account record and specialised setting management.  
**Are transfers/payments available?** No.  
**How are accounts identified?** By subtype route and numeric record ID after creation; account number must be unique.  
**How does authentication work?** It is not yet implemented.  
**What if a request fails?** Correct client errors; use cautious bounded retry for transient failures and reconcile uncertain creates.  
**How is data protected?** Production controls are planned; no compliance claim is made.  
**How are versions handled?** The current API is unversioned; `/api/v1` is recommended before external rollout.
