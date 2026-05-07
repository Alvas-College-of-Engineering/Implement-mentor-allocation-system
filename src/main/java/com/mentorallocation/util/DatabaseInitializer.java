package com.mentorallocation.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseInitializer
 * Automatically initializes the H2 database with schema and sample data
 * Runs on application startup
 */
@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("==========================================");
        System.out.println("Initializing Mentor Allocation System Database");
        System.out.println("==========================================");
        
        try {
            initializeDatabase();
            System.out.println("✓ Database initialized successfully!");
            System.out.println("==========================================");
        } catch (SQLException e) {
            System.err.println("✗ Failed to initialize database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Initialize database schema and populate sample data
     */
    private void initializeDatabase() throws SQLException {
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement()) {

            // Drop existing tables (if any) for fresh start
            dropTablesIfExist(stmt);

            // Create mentors table
            System.out.println("Creating mentors table...");
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS mentors (" +
                "    mentorId INT AUTO_INCREMENT PRIMARY KEY," +
                "    name VARCHAR(100) NOT NULL," +
                "    specialization VARCHAR(100) NOT NULL," +
                "    availability BOOLEAN NOT NULL DEFAULT true," +
                "    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "    UNIQUE KEY unique_mentor_name (name, specialization)" +
                ")"
            );

            // Create students table
            System.out.println("Creating students table...");
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS students (" +
                "    studentId INT AUTO_INCREMENT PRIMARY KEY," +
                "    name VARCHAR(100) NOT NULL," +
                "    department VARCHAR(100) NOT NULL," +
                "    requiredSpecialization VARCHAR(100) NOT NULL," +
                "    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "    UNIQUE KEY unique_student_email (name, department)" +
                ")"
            );

            // Create allocations table
            System.out.println("Creating allocations table...");
            stmt.execute(
                "CREATE TABLE IF NOT EXISTS allocations (" +
                "    allocationId INT AUTO_INCREMENT PRIMARY KEY," +
                "    mentorId INT NOT NULL," +
                "    studentId INT NOT NULL," +
                "    allocationDate DATE NOT NULL," +
                "    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "    FOREIGN KEY (mentorId) REFERENCES mentors(mentorId) ON DELETE CASCADE," +
                "    FOREIGN KEY (studentId) REFERENCES students(studentId) ON DELETE CASCADE," +
                "    UNIQUE KEY unique_allocation (mentorId, studentId)" +
                ")"
            );

            // Insert sample mentors
            System.out.println("Inserting sample mentors...");
            stmt.execute(
                "INSERT INTO mentors (name, specialization, availability) VALUES " +
                "('Dr. Rajesh Kumar', 'Java', true)," +
                "('Prof. Priya Singh', 'Python', true)," +
                "('Mr. Amit Patel', 'Web Development', true)," +
                "('Ms. Neha Sharma', 'Database Design', true)," +
                "('Dr. Vikram Verma', 'Cloud Computing', true)," +
                "('Prof. Sarah Johnson', 'Machine Learning', true)"
            );

            // Insert sample students
            System.out.println("Inserting sample students...");
            stmt.execute(
                "INSERT INTO students (name, department, requiredSpecialization) VALUES " +
                "('Arjun Mishra', 'Computer Science', 'Java')," +
                "('Bhavna Gupta', 'Information Technology', 'Python')," +
                "('Chirag Desai', 'Computer Science', 'Web Development')," +
                "('Disha Rao', 'Information Technology', 'Database Design')," +
                "('Eshan Kumar', 'Computer Science', 'Cloud Computing')," +
                "('Fiona Chen', 'Information Technology', 'Machine Learning')"
            );

            System.out.println("✓ Schema created and sample data populated!");

        } catch (SQLException e) {
            System.err.println("Error during database initialization: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Drop existing tables if they exist
     */
    private void dropTablesIfExist(Statement stmt) throws SQLException {
        try {
            stmt.execute("DROP TABLE IF EXISTS allocations");
            stmt.execute("DROP TABLE IF EXISTS students");
            stmt.execute("DROP TABLE IF EXISTS mentors");
            System.out.println("Previous tables dropped (fresh start)");
        } catch (SQLException e) {
            // Tables might not exist - that's okay on first run
            System.out.println("No existing tables to drop");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Mentor Allocation System shutting down");
    }
}
