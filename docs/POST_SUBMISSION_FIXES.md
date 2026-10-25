# Post-submission fixes included in this repository

The repository includes the final fixes made after the original Group 16 submission:

- Emergency vehicle identifiers are validated in both the browser and server as exactly two uppercase letters followed by three digits (`AB123`).
- Registration email validation rejects extra `@` characters, spaces, `=` and other unsupported address forms.
- Public registration always creates a `PUBLIC` role. No role field or request parameter can promote a registering user to administrator.
- Spring Security continues to enforce administrator/operator/authority/emergency URL access on the server.
- Five-second traffic refresh updates values inside the existing junction card instead of replacing the card. This preserves the correct junction name, manual-override form and hidden junction ID even if API results arrive in a different order.
- The corridor display uses a responsive four-stop layout so the Nasinu junction is fully visible at desktop, tablet and mobile widths.
- Visible references to generated/AI artwork were removed from the final interface and documentation.
- Server port remains `8080`; Java remains JDK 17; MariaDB/XAMPP configuration remains `tmms_database` / `tmms_app`.
