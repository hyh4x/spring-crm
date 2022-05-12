# spring-crm
CRM project developed with Spring MVC, JSP, Spring Security, PostgreSQL

It needs two databases: 
- *web_customer_tracker* for holding customer information
- *spring_security_bcrypt* for holding users login credentials
pgdump files for databases are in `pgdump/`

Existing users:
- user: jesus, password: qwerty, role: employee
- user: mary, password: qwerty, roles: employee, manager
- user: joseph, password: qwerty, roles: employee, admin
