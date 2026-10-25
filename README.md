# Traffic Monitoring and Management System

**CS241 Assignment 2 — Group 16**

This Spring Boot website demonstrates traffic monitoring and management along the Suva–Nausori corridor. The monitored junctions are kept in this order:

**Nausori → Nakasi → Nasinu → Valelevu**

## Main features

- Separate role-based access for administrators, TMC operators, FRA/LTA authorities, emergency services and public users.
- Public self-registration only; administrative roles cannot be selected during sign-up.
- Simulated traffic readings including vehicle count, speed, queue length, occupancy, congestion and road condition.
- Live traffic status refresh every 5 seconds and stored history snapshots every 30 seconds.
- Congestion classification: GREEN, YELLOW and RED.
- Adaptive signal timing using queue length, with green time bounded between 15 and 60 seconds.
- Automatic incident detection when speed remains below 10 km/h for more than 15 seconds.
- Emergency vehicle priority for 30 seconds for ambulance, police and fire vehicles.
- Manual signal override for authorized staff for 60 seconds, blocked while emergency priority is active.
- Incident reporting, alerts, device status and traffic-control audit logs.
- Administrator shortcut to phpMyAdmin. The shortcut opens phpMyAdmin but does not automatically sign in.

## Tools

- Java 17
- Spring Boot 3.4.5
- Thymeleaf
- Spring Security
- Spring Data JPA / Hibernate
- MariaDB through XAMPP
- Maven
- Eclipse IDE or another Java IDE

## Database setup

1. Start **Apache** and **MySQL** in XAMPP.
2. Open `http://localhost/phpmyadmin/`.
3. Import `database/01_setup_xampp.sql` once.
4. The script creates the database and application user used by the project.

Database name: `tmms_database`  
Database username: `tmms_app`

The database login is separate from website user accounts.

The database contains seven main tables:

- `system_users`
- `junctions`
- `traffic_observations`
- `incidents`
- `alerts`
- `traffic_control_logs`
- `traffic_devices`

## Run the application

1. Open this project in Eclipse.
2. Confirm the project is using **JDK 17**.
3. Start XAMPP Apache and MySQL.
4. Run `TmmsApplication` as **Spring Boot App**.
5. Open `http://localhost:8080/login`.

The server port is `8080`.

## Demo accounts

The application creates demo accounts on first start if they do not already exist:

| Role | Email | Password |
|---|---|---|
| Administrator | `admin@tmms.local` | `Admin123!` |
| TMC operator | `operator@tmms.local` | `Operator123!` |
| FRA/LTA authority | `authority@tmms.local` | `Authority123!` |
| Emergency services | `emergency@tmms.local` | `Emergency123!` |
| Public | `public@tmms.local` | `Public123!` |

These accounts are for the university demonstration only.

## Testing

The project includes unit and integration tests for traffic classification, adaptive timing, incident detection, registration validation and role-based URL access. Tests use H2 so the MariaDB database is not modified.

Run tests with:

```bash
mvn test
```

## Project structure

- `src/main/java/fj/usp/tmms` — Java source code
- `src/main/resources/templates` — Thymeleaf pages
- `src/main/resources/static` — CSS and JavaScript
- `database/01_setup_xampp.sql` — MariaDB/XAMPP setup
- `docs` — architecture, testing and demo notes
- `docs/POST_SUBMISSION_FIXES.md` — final changes applied after the submitted version
