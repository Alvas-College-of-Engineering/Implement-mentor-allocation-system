# Mentor Allocation System

This is the local project folder for the **Mentor Allocation System** web application.

The project is implemented as a Java Maven web application using:
- **Java 17**
- **Jakarta Servlet 6.0**
- **JSP/JSTL**
- **H2 in-memory database**
- **MVC architecture**
- **Tomcat 10+ deployment**

> Note: This folder is currently not connected to Git or GitHub.

## Project Overview

The Mentor Allocation System allows administrators to:
- add mentors
- add students
- allocate mentors to students
- view all mentor allocations

The application uses the following package structure:
- `com.mentorallocation.model`
- `com.mentorallocation.dao`
- `com.mentorallocation.service`
- `com.mentorallocation.servlet`
- `com.mentorallocation.util`

## Build and Run

### Prerequisites
- Java 17 SDK
- Apache Maven
- Apache Tomcat 10 or higher

### Build the WAR

From the project root:

```powershell
mvn clean package
```

### Deploy to Tomcat

Copy the generated WAR file from `target/MentorAllocationSystem.war` to your Tomcat `webapps` folder and start Tomcat.

### Access the application

Open a browser and go to:

```text
http://localhost:8080/MentorAllocationSystem/
```

## Notes

- The application uses an in-memory H2 database.
- The database is initialized automatically on application startup by `DatabaseInitializer`.
- If you want to keep the project local only, do not reinitialize a Git repository in this folder.
