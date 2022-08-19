## FWA

### Dependencies

* Spring Core
* Spring Context
* Spring JDBC
* Spring Security Core
* Java Servlet API
* H2 Database
* Lombok
* Cargo Maven plugin with Tomcat

### Features

* Registration
* Authentication
* Saving the current user in the session
* Embedded database with initial schema and data

### Quick start

**Step 1:** Make sure you have Java 1.8 & Maven package manager

**Step 2:** Run `mvn clean verify cargo:run` to build the project and run the application with embedded Tomcat server

### Pages

* Default URL: http://localhost:8080
* Login page: /signIn
* Register page: /signUp
* Empty profile page: /profile
