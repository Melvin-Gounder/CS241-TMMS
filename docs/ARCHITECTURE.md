# System Architecture

The TMMS uses a standard layered Spring Boot architecture:

1. **Presentation layer** — Thymeleaf pages, CSS and JavaScript.
2. **Controller layer** — handles login, registration, dashboards, traffic pages and JSON endpoints.
3. **Service layer** — traffic simulation, congestion rules, adaptive timing, incident detection, emergency priority and user registration.
4. **Repository layer** — Spring Data JPA repositories.
5. **Database layer** — MariaDB through XAMPP.
6. **Security** — Spring Security, BCrypt passwords and URL/role restrictions.

## Roles

- ADMIN — system administration and database tools.
- OPERATOR — TMC monitoring, manual override and traffic control.
- AUTHORITY — FRA/LTA monitoring and incident/traffic review.
- EMERGENCY — emergency-priority simulation and incident visibility.
- PUBLIC — public traffic information.

## Corridor model

Nausori → Nakasi → Nasinu → Valelevu

Each junction keeps the latest traffic status in memory for fast live display while periodic observations are persisted for history.
