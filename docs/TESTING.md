# Testing Notes

## Automated coverage

The included tests cover:

- GREEN/YELLOW/RED traffic classification.
- Adaptive green-time limits and queue-based calculation.
- Automatic incident detection threshold.
- Public registration and strict email validation.
- Vehicle identifier validation (`AA123` format: two uppercase letters followed by three digits).
- Role-based access to protected routes.

The test profile uses an H2 in-memory database so tests do not alter the XAMPP database.

## Manual checks

| ID | Check | Expected result |
|---|---|---|
| T01 | Open `/traffic` without logging in | Public traffic page loads |
| T02 | Register a public account | Account saved with PUBLIC role |
| T03 | Log in as administrator | Admin dashboard opens |
| T04 | Log in as operator | Operator dashboard opens |
| T05 | Attempt `/admin` as public user | Access denied |
| T06 | Wait for live traffic refresh | Junction values update |
| T07 | Trigger emergency priority | Selected junction gets 30-second priority |
| T08 | Start manual override during emergency | Request is rejected |
| T09 | Submit valid vehicle ID `AB123` | Accepted |
| T10 | Submit invalid vehicle ID `ABC12` | Rejected |
| T11 | Low speed persists longer than 15s | Automatic incident is created |
| T12 | Admin clicks Database tools | phpMyAdmin opens in a new tab |
