## FWA

### Dependencies

* Spring Core
* Spring Context
* Spring JDBC
* Spring Security Core (BCrypt)
* Java Servlet API
* H2 Database (in-memory)
* Lombok
* Cargo Maven plugin with Tomcat
* JSTL

### Features

* Registration
* Authentication
* Profile page
* Saving the current user in an HTTP session
* Embedded database with initial schema and data
* Logging of authorized users with date and IP
* Uploading a profile picture

### Quick start

**Step 1:** Make sure you have Java 1.8 & Maven package manager

**Step 2:** Run `mvn clean verify cargo:run` to build the project and run the application with embedded Tomcat server

### Pages

* Default URL: http://localhost:8080
* Login page: /signIn
* Register page: /signUp
* Profile page: /profile
