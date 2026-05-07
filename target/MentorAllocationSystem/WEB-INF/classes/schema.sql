-- ====================================================================
-- Mentor Allocation System - SQL Schema
-- Database: mentor_db
-- ====================================================================

-- Drop existing database if it exists
DROP DATABASE IF EXISTS mentor_db;

-- Create database
CREATE DATABASE mentor_db;
USE mentor_db;

-- ====================================================================
-- Create Mentors Table
-- ====================================================================
CREATE TABLE mentors (
    mentorId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    specialization VARCHAR(100) NOT NULL,
    availability BOOLEAN NOT NULL DEFAULT true,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_mentor_name (name, specialization)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index on specialization and availability for faster queries
CREATE INDEX idx_specialization_availability ON mentors(specialization, availability);

-- ====================================================================
-- Create Students Table
-- ====================================================================
CREATE TABLE students (
    studentId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100) NOT NULL,
    requiredSpecialization VARCHAR(100) NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY unique_student_email (name, department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Index on required specialization for faster queries
CREATE INDEX idx_required_specialization ON students(requiredSpecialization);

-- ====================================================================
-- Create Allocations Table
-- ====================================================================
CREATE TABLE allocations (
    allocationId INT AUTO_INCREMENT PRIMARY KEY,
    mentorId INT NOT NULL,
    studentId INT NOT NULL,
    allocationDate DATE NOT NULL,
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    -- Foreign key constraints
    FOREIGN KEY (mentorId) REFERENCES mentors(mentorId) ON DELETE CASCADE,
    FOREIGN KEY (studentId) REFERENCES students(studentId) ON DELETE CASCADE,
    
    -- Unique constraint to prevent duplicate allocations
    UNIQUE KEY unique_allocation (mentorId, studentId)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Indexes for faster queries
CREATE INDEX idx_mentor_allocation ON allocations(mentorId);
CREATE INDEX idx_student_allocation ON allocations(studentId);
CREATE INDEX idx_allocation_date ON allocations(allocationDate);

-- ====================================================================
-- Sample Data Insertion
-- ====================================================================

-- Insert Sample Mentors
INSERT INTO mentors (name, specialization, availability) VALUES
('Dr. Rajesh Kumar', 'Java', true),
('Prof. Priya Singh', 'Python', true),
('Mr. Amit Patel', 'Web Development', true),
('Ms. Neha Sharma', 'Database Design', true),
('Dr. Vikram Verma', 'Cloud Computing', true),
('Prof. Sarah Johnson', 'Machine Learning', true);

-- Insert Sample Students
INSERT INTO students (name, department, requiredSpecialization) VALUES
('Arjun Mishra', 'Computer Science', 'Java'),
('Bhavna Gupta', 'Information Technology', 'Python'),
('Chirag Desai', 'Computer Science', 'Web Development'),
('Disha Rao', 'Information Technology', 'Database Design'),
('Eshan Kumar', 'Computer Science', 'Cloud Computing'),
('Fiona Chen', 'Information Technology', 'Machine Learning');

-- ====================================================================
-- Verification Queries
-- ====================================================================

-- View all mentors
SELECT * FROM mentors;

-- View all students
SELECT * FROM students;

-- View all allocations with mentor and student names
SELECT 
    a.allocationId,
    m.name AS MentorName,
    s.name AS StudentName,
    m.specialization,
    a.allocationDate
FROM allocations a
JOIN mentors m ON a.mentorId = m.mentorId
JOIN students s ON a.studentId = s.studentId;

-- ====================================================================
-- End of SQL Schema
-- ====================================================================
