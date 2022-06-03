# spring-crm
CRM project developed with Spring MVC, JSP, Spring Security, PostgreSQL

Now runs in Docker containers!

Run with `docker-compose build && docker-compose up -d`

Access at http://localhost:8080/crm

Users have access to different actions based on roles

Existing users:
- User: **jesus**, Password: **qwerty**, role: Employee - Read
- User: **mary**, Password: **qwerty**, roles: Employee, Manager - Create, Read, Update
- User: **joseph**, Password: **qwerty**, roles: Employee, Admin - Create, Read, Update, Delete

You can create new User with Read-Only rights
