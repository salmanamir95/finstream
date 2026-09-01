# Account management guide

Use the current-account routes for current accounts and savings-account routes for savings accounts. An account number must be unique. Request bodies currently carry customer ID and account number; account status is not currently managed through the API response/request. Treat API failures as described in the error guide and avoid automatic unsafe retries of create requests.
