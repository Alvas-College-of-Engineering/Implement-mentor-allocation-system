package com.mentorallocation.dao;

import com.mentorallocation.model.Student;
import com.mentorallocation.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO Class
 * Data Access Object for Student model
 * Handles all CRUD operations for students
 */
public class StudentDAO {

    /**
     * Insert a new student into the database
     * @param student Student object to insert
     * @return true if insertion is successful, false otherwise
     */
    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO students (name, department, requiredSpecialization) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters using PreparedStatement
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setString(3, student.getRequiredSpecialization());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get student by ID
     * @param studentId ID of the student to retrieve
     * @return Student object or null if not found
     */
    public Student getStudentById(int studentId) {
        String sql = "SELECT * FROM students WHERE studentId = ?";
        Student student = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("studentId"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("requiredSpecialization")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
            e.printStackTrace();
        }

        return student;
    }

    /**
     * Get all students from the database
     * @return List of all Student objects
     */
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("studentId"),
                        rs.getString("name"),
                        rs.getString("department"),
                        rs.getString("requiredSpecialization")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving all students: " + e.getMessage());
            e.printStackTrace();
        }

        return students;
    }

    /**
     * Update student information
     * @param student Student object with updated information
     * @return true if update is successful, false otherwise
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, department = ?, requiredSpecialization = ? WHERE studentId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getDepartment());
            pstmt.setString(3, student.getRequiredSpecialization());
            pstmt.setInt(4, student.getStudentId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a student by ID
     * @param studentId ID of the student to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE studentId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
