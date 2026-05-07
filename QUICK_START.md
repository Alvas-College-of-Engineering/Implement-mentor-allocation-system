# Quick Start

## Prerequisites
- Java 17 JDK installed
- Apache Maven installed
- Apache Tomcat 10 or newer installed

## Build the Project
1. Open a terminal in the project root:
   ```powershell
   cd "c:\Users\megha\OneDrive\Desktop\AJ\MentorAllocationSystem"
   ```
2. Run Maven package:
   ```powershell
   mvn clean package
   ```
3. Confirm the WAR file is generated:
   ```powershell
   dir target\MentorAllocationSystem.war
   ```

## Deploy to Tomcat
1. Copy the WAR to Tomcat's `webapps` directory:
   ```powershell
   copy target\MentorAllocationSystem.war "C:\path\to\tomcat\webapps\"
   ```
2. Start Tomcat.
3. Open the application:
   ```text
   http://localhost:8080/MentorAllocationSystem/
   ```

## Notes
- The application uses an H2 in-memory database.
- The database is initialized automatically on startup.
- No database setup is required for local testing.
