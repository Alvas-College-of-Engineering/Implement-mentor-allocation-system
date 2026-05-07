# Quick Start Guide - Mentor Allocation System

## Prerequisites Checklist
- [ ] JDK 17 installed
- [ ] Maven 3.9+ installed
- [ ] Apache Tomcat 10 installed
- [ ] MySQL 8 installed and running
- [ ] VS Code with Java Extension Pack installed

## 30-Minute Quick Setup

### 1. Database Setup (2 minutes)
```bash
# Open MySQL command line
mysql -u root -p

# Run the schema
source C:\Users\megha\OneDrive\Desktop\AJ\MentorAllocationSystem\src\main\resources\schema.sql
```

### 2. Build Project (3 minutes)
```bash
cd C:\Users\megha\OneDrive\Desktop\AJ\MentorAllocationSystem
mvn clean install
```

### 3. Configure Tomcat in VS Code (2 minutes)
1. Open VS Code
2. Ctrl + Shift + P → "Tomcat: Add Tomcat Server"
3. Select Tomcat 10 folder

### 4. Update Database Credentials (1 minute)
Edit: `src/main/java/com/mentorallocation/util/DBConnection.java`
```java
private static final String USER = "root";        // Change if needed
private static final String PASSWORD = "root";    // Change if needed
```

### 5. Deploy & Run (2 minutes)
1. Right-click Tomcat server in VS Code Explorer
2. Select "Run"
3. Right-click project → "Run on Tomcat Server"

### 6. Access Application (1 minute)
```
http://localhost:8080/MentorAllocationSystem/
```

## File Locations Reference

| File | Location |
|------|----------|
| Java Source Code | `src/main/java/com/mentorallocation/` |
| JSP Pages | `src/main/webapp/` |
| SQL Schema | `src/main/resources/schema.sql` |
| Web Config | `src/main/webapp/WEB-INF/web.xml` |
| Build Config | `pom.xml` |
| Documentation | `README.md` |

## Common Commands

### Build Project
```bash
mvn clean install
```

### Run Tests
```bash
mvn test
```

### Generate WAR File
```bash
mvn package
```

### View Maven Dependency Tree
```bash
mvn dependency:tree
```

### Clean Generated Files
```bash
mvn clean
```

## Port Configuration

- **Tomcat**: http://localhost:8080/
- **Application**: http://localhost:8080/MentorAllocationSystem/
- **MySQL**: localhost:3306

If port 8080 is busy, modify `apache-tomcat-10/conf/server.xml`:
```xml
<Connector port="8081" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```

## Important URLs

| Feature | URL |
|---------|-----|
| Home | http://localhost:8080/MentorAllocationSystem/ |
| Add Mentor | http://localhost:8080/MentorAllocationSystem/addMentor |
| Add Student | http://localhost:8080/MentorAllocationSystem/addStudent |
| Allocate Mentor | http://localhost:8080/MentorAllocationSystem/allocateMentor |
| View Allocations | http://localhost:8080/MentorAllocationSystem/viewAllocations |

## Database Login

```bash
# Connect to MySQL
mysql -u root -p

# Use the database
USE mentor_db;

# View all tables
SHOW TABLES;

# View mentors
SELECT * FROM mentors;

# View students
SELECT * FROM students;

# View allocations
SELECT * FROM allocations;
```

## VS Code Keyboard Shortcuts

| Action | Shortcut |
|--------|----------|
| Open Terminal | Ctrl + ` |
| Command Palette | Ctrl + Shift + P |
| Quick File Open | Ctrl + P |
| Find in Files | Ctrl + Shift + F |
| Debug | F5 |

## Troubleshooting Quick Reference

### Problem: Maven build fails
**Solution**: `mvn clean install -U` (update dependencies)

### Problem: Can't connect to MySQL
**Solution**: Verify MySQL is running and credentials are correct

### Problem: Tomcat won't start
**Solution**: Check if port 8080 is available or change in server.xml

### Problem: JSP pages show blank
**Solution**: Check Tomcat logs for errors, rebuild with `mvn clean install`

## Sample Test Flow

1. **Add Mentor**: Go to Add Mentor page → Enter "John Doe", "Java" → Save
2. **Add Student**: Go to Add Student page → Enter "Jane Smith", "CS", "Java" → Save
3. **Allocate**: Go to Allocate page → Enter Mentor ID=1, Student ID=1 → Allocate
4. **Verify**: Go to View Allocations → See allocation in table

## Next Steps

1. Review [README.md](README.md) for detailed documentation
2. Examine code in `src/main/java/com/mentorallocation/`
3. Customize database credentials as needed
4. Deploy to production Tomcat server
5. Add more features as required

## Emergency Shutdown

```bash
# Find Tomcat process
netstat -ano | findstr :8080

# Kill process (replace PID with actual process ID)
taskkill /PID <PID> /F

# Or directly
C:\apache-tomcat-10\bin\catalina.bat stop
```

## Support

For detailed setup and troubleshooting, see the main [README.md](README.md) file.

---
**Created**: May 6, 2026
**Project**: Mentor Allocation System v1.0.0
