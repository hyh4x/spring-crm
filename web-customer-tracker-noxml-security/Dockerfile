FROM maven:3.8.5-openjdk-8-slim as build
WORKDIR usr/src/app
COPY pom.xml pom.xml
RUN mvn verify --fail-never
COPY . .
RUN mvn package

FROM tomcat:9.0.62-jdk8-openjdk
COPY --from=build /usr/src/app/target/web-customer-tracker-security-0.1.war /usr/local/tomcat/webapps/crm.war
