package com.mentorallocation.dao;

import com.mentorallocation.model.Mentor;
import com.mentorallocation.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MentorDAO Class
 * Data Access Object for Mentor model
 * Handles all CRUD operations for mentors
 */
public class MentorDAO {

    /**
     * Insert a new mentor into the database
     * @param mentor Mentor object to insert
     * @return true if insertion is successful, false otherwise
     */
    public boolean insertMentor(Mentor mentor) {
        String sql = "INSERT INTO mentors (name, specialization, availability) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters using PreparedStatement
            pstmt.setString(1, mentor.getName());
            pstmt.setString(2, mentor.getSpecialization());
            pstmt.setBoolean(3, mentor.isAvailability());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting mentor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get mentor by ID
     * @param mentorId ID of the mentor to retrieve
     * @return Mentor object or null if not found
     */
    public Mentor getMentorById(int mentorId) {
        String sql = "SELECT * FROM mentors WHERE mentorId = ?";
        Mentor mentor = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, mentorId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                mentor = new Mentor(
                        rs.getInt("mentorId"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getBoolean("availability")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving mentor: " + e.getMessage());
            e.printStackTrace();
        }

        return mentor;
    }

    /**
     * Get all mentors from the database
     * @return List of all Mentor objects
     */
    public List<Mentor> getAllMentors() {
        String sql = "SELECT * FROM mentors";
        List<Mentor> mentors = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Mentor mentor = new Mentor(
                        rs.getInt("mentorId"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getBoolean("availability")
                );
                mentors.add(mentor);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving all mentors: " + e.getMessage());
            e.printStackTrace();
        }

        return mentors;
    }

    /**
     * Get all available mentors with specific specialization
     * @param specialization Specialization to filter by
     * @return List of available Mentor objects with the specialization
     */
    public List<Mentor> getAvailableMentorsBySpecialization(String specialization) {
        String sql = "SELECT * FROM mentors WHERE specialization = ? AND availability = true";
        List<Mentor> mentors = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, specialization);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Mentor mentor = new Mentor(
                        rs.getInt("mentorId"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getBoolean("availability")
                );
                mentors.add(mentor);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving available mentors: " + e.getMessage());
            e.printStackTrace();
        }

        return mentors;
    }

    /**
     * Update mentor information
     * @param mentor Mentor object with updated information
     * @return true if update is successful, false otherwise
     */
    public boolean updateMentor(Mentor mentor) {
        String sql = "UPDATE mentors SET name = ?, specialization = ?, availability = ? WHERE mentorId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, mentor.getName());
            pstmt.setString(2, mentor.getSpecialization());
            pstmt.setBoolean(3, mentor.isAvailability());
            pstmt.setInt(4, mentor.getMentorId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating mentor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update mentor availability status
     * @param mentorId ID of the mentor
     * @param availability New availability status
     * @return true if update is successful, false otherwise
     */
    public boolean updateMentorAvailability(int mentorId, boolean availability) {
        String sql = "UPDATE mentors SET availability = ? WHERE mentorId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setBoolean(1, availability);
            pstmt.setInt(2, mentorId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating mentor availability: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a mentor by ID
     * @param mentorId ID of the mentor to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean deleteMentor(int mentorId) {
        String sql = "DELETE FROM mentors WHERE mentorId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, mentorId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting mentor: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
