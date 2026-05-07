# Mentor Allocation System

## Project Overview
A complete Java Maven Dynamic Web Project for managing mentor-student allocations. Built using JSP, Servlets, JDBC, and MySQL with Apache Tomcat 10. The system implements an MVC architecture with proper separation of concerns.

## Technology Stack
- **Language**: Java (JDK 17)
- **Framework**: Jakarta Servlet API 6.0 (Tomcat 10 compatible)
- **Application Server**: Apache Tomcat 10
- **Database**: H2 in-memory database
- **Build Tool**: Maven 3.9+
- **View Layer**: JSP (JavaServer Pages)
- **Template Library**: JSTL (JavaServer Pages Standard Tag Library)
- **Database Driver**: H2 Database Engine

## Project Structure
```
MentorAllocationSystem/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mentorallocation/
│   │   │       ├── model/
│   │   │       │   ├── Mentor.java
│   │   │       │   ├── Student.java
│   │   │       │   └── Allocation.java
│   │   │       ├── dao/
│   │   │       │   ├── MentorDAO.java
│   │   │       │   ├── StudentDAO.java
│   │   │       │   └── AllocationDAO.java
│   │   │       ├── servlet/
│   │   │       │   ├── AddMentorServlet.java
│   │   │       │   ├── AddStudentServlet.java
│   │   │       │   ├── AllocateMentorServlet.java
│   │   │       │   └── ViewAllocationsServlet.java
│   │   │       ├── util/
│   │   │       │   └── DBConnection.java
│   │   │       └── service/
│   │   │           └── AllocationService.java
│   │   ├── webapp/
│   │   │   ├── index.jsp
│   │   │   ├── addMentor.jsp
│   │   │   ├── addStudent.jsp
│   │   │   ├── allocateMentor.jsp
│   │   │   ├── viewAllocations.jsp
│   │   │   └── WEB-INF/
│   │   │       └── web.xml
│   │   └── resources/
│   │       └── schema.sql
├── pom.xml
└── README.md
```

## Prerequisites

### 1. System Requirements
- **Windows 10/11** (or any OS supporting Java 17)
- **JDK 17** or higher
- **Apache Tomcat 10**
- **MySQL 8** or higher
- **Maven 3.9+**

### 2. Install Required Software

#### Install JDK 17
1. Download from: https://www.oracle.com/java/technologies/downloads/#java17
2. Run the installer and follow the installation wizard
3. Set `JAVA_HOME` environment variable to JDK installation path
4. Verify installation:
   ```bash
   java -version
   javac -version
   ```

#### Install Maven
1. Download from: https://maven.apache.org/download.cgi
2. Extract to a folder (e.g., `C:\apache-maven-3.9.0`)
3. Add Maven `bin` folder to PATH environment variable
4. Verify installation:
   ```bash
   mvn -version
   ```

#### Install Apache Tomcat 10
1. Download from: https://tomcat.apache.org/download-10.cgi
2. Extract to a folder (e.g., `C:\apache-tomcat-10.1.0`)
3. No installation required
4. The `CATALINA_HOME` will be the extracted folder path

#### Install VS Code Extensions
1. Open VS Code
2. Install these extensions:
   - **Extension Pack for Java** (Microsoft)
   - **Apache Tomcat for Java** (Microsoft)
   - **MySQL** (Jun Han)
   - **Maven for Java** (Microsoft)

## Database Setup

This project uses an embedded H2 in-memory database and initializes schema/sample data automatically when the app starts. No external database installation is required for evaluation.

### Notes
- The database is created on application startup
- Sample mentors and students are inserted automatically
- Data is not persisted after the server stops, which is fine for submission and evaluation

## Application Features

### 1. Add Mentor
- **URL**: `http://localhost:8080/MentorAllocationSystem/addMentor`
- **Fields**: Name, Specialization, Availability
- **Specializations**: Java, Python, Web Development, Database Design, Cloud Computing, Machine Learning
- **Action**: Adds new mentor with availability status

### 2. Add Student
- **URL**: `http://localhost:8080/MentorAllocationSystem/addStudent`
- **Fields**: Name, Department, Required Specialization
- **Departments**: Computer Science, Information Technology, Electronics, Mechanical, Electrical
- **Action**: Registers new student with specialization requirements

### 3. Allocate Mentor
- **URL**: `http://localhost:8080/MentorAllocationSystem/allocateMentor`
- **Rules**:
  - Mentor specialization must match student's required specialization
  - Mentor must be available (availability = true)
  - After allocation, mentor's availability is set to false
- **Action**: Creates allocation record and updates mentor availability

### 4. View Allocations
- **URL**: `http://localhost:8080/MentorAllocationSystem/viewAllocations`
- **Display**: Table with all mentor-student allocations
- **Columns**: Allocation ID, Mentor Name, Student Name, Specialization, Allocation Date

## Step-by-Step Setup in VS Code

### Step 1: Open Project in VS Code
1. Open VS Code
2. File → Open Folder → Select `MentorAllocationSystem` folder
3. Wait for extensions to initialize and index the project

### Step 2: Build the Project
1. Open Terminal (Ctrl + `)
2. Navigate to project root:
   ```bash
   cd c:\Users\megha\OneDrive\Desktop\AJ\MentorAllocationSystem
   ```
3. Build using Maven:
   ```bash
   mvn clean install
   ```
4. Wait for build to complete (should show `BUILD SUCCESS`)

### Step 3: Configure Tomcat in VS Code
1. Press Ctrl + Shift + P (Command Palette)
2. Search for "Tomcat: Add Tomcat Server"
3. Select the Tomcat 10 installation folder
4. Tomcat server will be added to the explorer

### Step 4: Update Database Credentials
1. Open `src/main/java/com/mentorallocation/util/DBConnection.java`
2. Modify these lines if your MySQL credentials differ:
   ```java
   private static final String USER = "root";        // MySQL username
   private static final String PASSWORD = "root";    // MySQL password
   ```
3. Save the file
4. Rebuild project: `mvn clean install`

### Step 5: Deploy on Tomcat
1. In VS Code Explorer, look for "Tomcat Servers" section
2. Right-click on your Tomcat server
3. Select "Run"
4. Tomcat server will start
5. In VS Code Explorer, look for your Tomcat server under "Tomcat Servers"
6. Right-click on the project → "Run on Tomcat Server"

Alternatively, use the generated WAR file:
1. Build the project:
   ```bash
   mvn clean package
   ```
2. Copy `target/MentorAllocationSystem.war` into `TOMCAT_HOME/webapps`
3. Start Tomcat with `startup.bat`

### Step 6: Access the Application
1. Open browser and go to:
   ```
   http://localhost:8080/MentorAllocationSystem/
   ```
   http://localhost:8080/MentorAllocationSystem/
   ```
2. You should see the home page with navigation buttons

## Building from Command Line

### Build the WAR File
```bash
cd c:\Users\megha\OneDrive\Desktop\AJ\MentorAllocationSystem
mvn clean package
```

### Deploy to Tomcat
1. Copy the generated WAR file from `target/MentorAllocationSystem.war`
2. Paste it in Tomcat's `webapps` folder: `C:\apache-tomcat-10.1.0\webapps\`
3. Tomcat will automatically extract and deploy the application

### Start Tomcat (Command Line)
```bash
cd C:\apache-tomcat-10.1.0\bin
catalina.bat run
```

### Stop Tomcat (Command Line)
```bash
cd C:\apache-tomcat-10.1.0\bin
catalina.bat stop
```

## Troubleshooting

### Issue: "Database connection failed"
**Solution**: 
1. Verify MySQL is running: `mysql -u root -p`
2. Check credentials in `DBConnection.java`
3. Verify database `mentor_db` exists: `SHOW DATABASES;`

### Issue: "Class not found: com.mysql.cj.jdbc.Driver"
**Solution**:
1. Run `mvn clean install` to download MySQL driver
2. Verify driver JAR exists in `target/MentorAllocationSystem/WEB-INF/lib/`

### Issue: "Tomcat server not starting"
**Solution**:
1. Check if port 8080 is in use
2. Try changing Tomcat port in `conf/server.xml`
3. Check Tomcat logs: `logs/catalina.out`

### Issue: "404 - Page not found"
**Solution**:
1. Verify application URL: `http://localhost:8080/MentorAllocationSystem/`
2. Check Tomcat console for deployment errors
3. Verify JSP files are in `src/main/webapp/`

### Issue: "Deployment fails"
**Solution**:
1. Clean and rebuild: `mvn clean install`
2. Delete old deployment: Delete `MentorAllocationSystem` folder from Tomcat's `webapps`
3. Restart Tomcat

## Database Architecture

### Mentors Table
```sql
CREATE TABLE mentors (
    mentorId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    availability BOOLEAN NOT NULL DEFAULT true
);
```

### Students Table
```sql
CREATE TABLE students (
    studentId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    requiredSpecialization VARCHAR(100) NOT NULL
);
```

### Allocations Table
```sql
CREATE TABLE allocations (
    allocationId INT AUTO_INCREMENT PRIMARY KEY,
    mentorId INT NOT NULL,
    studentId INT NOT NULL,
    allocationDate DATE NOT NULL,
    FOREIGN KEY (mentorId) REFERENCES mentors(mentorId),
    FOREIGN KEY (studentId) REFERENCES students(studentId)
);
```

## Code Highlights

### MVC Architecture
- **Model**: Mentor, Student, Allocation classes with POJO pattern
- **View**: JSP pages with responsive CSS styling
- **Controller**: Servlets handling HTTP requests and business logic

### Database Best Practices
- **PreparedStatement**: All queries use PreparedStatement to prevent SQL injection
- **Connection Pooling**: Proper connection management with DBConnection utility
- **Exception Handling**: Comprehensive error handling and logging

### Business Logic
- **Allocation Validation**: Specialization matching and availability checking
- **Data Validation**: Input validation in both JSP forms and servlets
- **Atomic Operations**: Allocation and availability update as a transaction

### Security Features
- **SQL Injection Prevention**: PreparedStatement usage throughout
- **Input Validation**: Client-side and server-side validation
- **Exception Handling**: Proper error messages without exposing system details

## Sample Data

### Default Mentors
- Dr. Rajesh Kumar - Java
- Prof. Priya Singh - Python
- Mr. Amit Patel - Web Development
- Ms. Neha Sharma - Database Design
- Dr. Vikram Verma - Cloud Computing
- Prof. Sarah Johnson - Machine Learning

### Default Students
- Arjun Mishra - Computer Science (Java)
- Bhavna Gupta - Information Technology (Python)
- Chirag Desai - Computer Science (Web Development)
- Disha Rao - Information Technology (Database Design)
- Eshan Kumar - Computer Science (Cloud Computing)
- Fiona Chen - Information Technology (Machine Learning)

## Testing the Application

### Test Case 1: Add a Mentor
1. Go to http://localhost:8080/MentorAllocationSystem/addMentor
2. Fill form: Name="John Doe", Specialization="Java", Availability=checked
3. Click "Add Mentor"
4. Verify success message

### Test Case 2: Add a Student
1. Go to http://localhost:8080/MentorAllocationSystem/addStudent
2. Fill form: Name="Jane Smith", Department="Computer Science", RequiredSpecialization="Java"
3. Click "Add Student"
4. Verify success message

### Test Case 3: Allocate Mentor
1. Go to http://localhost:8080/MentorAllocationSystem/allocateMentor
2. Enter: Mentor ID=1, Student ID=1 (ensure specialization matches)
3. Click "Allocate Mentor"
4. Verify success and check that mentor availability is updated

### Test Case 4: View Allocations
1. Go to http://localhost:8080/MentorAllocationSystem/viewAllocations
2. Verify allocation records are displayed in table format

## Additional Resources

- [Jakarta EE Documentation](https://jakarta.ee/)
- [Apache Tomcat 10 Documentation](https://tomcat.apache.org/tomcat-10.0-doc/)
- [MySQL 8 Documentation](https://dev.mysql.com/doc/mysql-en/)
- [JSTL Reference](https://jakarta.ee/specifications/tags/)
- [Maven Documentation](https://maven.apache.org/guides/)

## Support and Contribution

For issues, enhancements, or questions:
1. Check the Troubleshooting section above
2. Review the code comments for implementation details
3. Verify database schema matches the SQL file

## License

This project is provided as-is for educational purposes.

## Author

Mentor Allocation System - Java Maven Dynamic Web Project for Tomcat 10

---
**Last Updated**: May 6, 2026
**Project Version**: 1.0.0
**Status**: Ready for Production
