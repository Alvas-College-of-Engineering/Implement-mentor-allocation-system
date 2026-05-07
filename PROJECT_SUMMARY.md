# Project Summary

## Mentor Allocation System

This application is a Java web-based solution for assigning mentors to students.
It is structured using MVC architecture and uses servlet-based controllers, JSP views, and utility classes for database management.

## Key Components

- `src/main/java/com/mentorallocation/model`
  - Domain model classes for Mentor, Student, Allocation

- `src/main/java/com/mentorallocation/dao`
  - Data access objects for database operations

- `src/main/java/com/mentorallocation/service`
  - Business logic layer for allocation rules and validation

- `src/main/java/com/mentorallocation/servlet`
  - Servlets handling add, allocate, and view operations

- `src/main/java/com/mentorallocation/util`
  - `DBConnection` for H2 database connectivity
  - `DatabaseInitializer` for auto schema creation and sample data insertion

## Functionality

- Add mentors and students through web forms
- Allocate mentors to students based on specialization match and availability
- View existing allocations in a table
- Use an in-memory H2 database for portability

## Architecture

- Model: business entities (`Mentor`, `Student`, `Allocation`)
- View: JSP pages (`index.jsp`, `addMentor.jsp`, `addStudent.jsp`, `allocateMentor.jsp`, `viewAllocations.jsp`)
- Controller: servlets (`AddMentorServlet`, `AddStudentServlet`, `AllocateMentorServlet`, `ViewAllocationsServlet`)
- Database: H2 in-memory database initiated on startup

## Deployment

- Use Maven to build the application as a WAR package
- Deploy the WAR to an Apache Tomcat 10 server
- Access from `http://localhost:8080/MentorAllocationSystem/`

## Notes

This folder is intended for local development and testing only; it is not currently connected to GitHub.
