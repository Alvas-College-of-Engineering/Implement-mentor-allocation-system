package com.mentorallocation.dao;

import com.mentorallocation.model.Allocation;
import com.mentorallocation.util.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * AllocationDAO Class
 * Data Access Object for Allocation model
 * Handles all CRUD operations for mentor-student allocations
 */
public class AllocationDAO {

    /**
     * Insert a new allocation into the database
     * @param allocation Allocation object to insert
     * @return true if insertion is successful, false otherwise
     */
    public boolean insertAllocation(Allocation allocation) {
        String sql = "INSERT INTO allocations (mentorId, studentId, allocationDate) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // Set parameters using PreparedStatement
            pstmt.setInt(1, allocation.getMentorId());
            pstmt.setInt(2, allocation.getStudentId());
            pstmt.setDate(3, Date.valueOf(allocation.getAllocationDate()));

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting allocation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get allocation by ID
     * @param allocationId ID of the allocation to retrieve
     * @return Allocation object or null if not found
     */
    public Allocation getAllocationById(int allocationId) {
        String sql = "SELECT * FROM allocations WHERE allocationId = ?";
        Allocation allocation = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, allocationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                allocation = new Allocation(
                        rs.getInt("allocationId"),
                        rs.getInt("mentorId"),
                        rs.getInt("studentId"),
                        rs.getDate("allocationDate").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving allocation: " + e.getMessage());
            e.printStackTrace();
        }

        return allocation;
    }

    /**
     * Get all allocations from the database
     * @return List of all Allocation objects
     */
    public List<Allocation> getAllAllocations() {
        String sql = "SELECT * FROM allocations";
        List<Allocation> allocations = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Allocation allocation = new Allocation(
                        rs.getInt("allocationId"),
                        rs.getInt("mentorId"),
                        rs.getInt("studentId"),
                        rs.getDate("allocationDate").toLocalDate()
                );
                allocations.add(allocation);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving all allocations: " + e.getMessage());
            e.printStackTrace();
        }

        return allocations;
    }

    /**
     * Get all allocations for a specific mentor
     * @param mentorId ID of the mentor
     * @return List of Allocation objects for the mentor
     */
    public List<Allocation> getAllocationsByMentorId(int mentorId) {
        String sql = "SELECT * FROM allocations WHERE mentorId = ?";
        List<Allocation> allocations = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, mentorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Allocation allocation = new Allocation(
                        rs.getInt("allocationId"),
                        rs.getInt("mentorId"),
                        rs.getInt("studentId"),
                        rs.getDate("allocationDate").toLocalDate()
                );
                allocations.add(allocation);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving allocations by mentor ID: " + e.getMessage());
            e.printStackTrace();
        }

        return allocations;
    }

    /**
     * Get all allocations for a specific student
     * @param studentId ID of the student
     * @return List of Allocation objects for the student
     */
    public List<Allocation> getAllocationsByStudentId(int studentId) {
        String sql = "SELECT * FROM allocations WHERE studentId = ?";
        List<Allocation> allocations = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Allocation allocation = new Allocation(
                        rs.getInt("allocationId"),
                        rs.getInt("mentorId"),
                        rs.getInt("studentId"),
                        rs.getDate("allocationDate").toLocalDate()
                );
                allocations.add(allocation);
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving allocations by student ID: " + e.getMessage());
            e.printStackTrace();
        }

        return allocations;
    }

    /**
     * Update allocation information
     * @param allocation Allocation object with updated information
     * @return true if update is successful, false otherwise
     */
    public boolean updateAllocation(Allocation allocation) {
        String sql = "UPDATE allocations SET mentorId = ?, studentId = ?, allocationDate = ? WHERE allocationId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, allocation.getMentorId());
            pstmt.setInt(2, allocation.getStudentId());
            pstmt.setDate(3, Date.valueOf(allocation.getAllocationDate()));
            pstmt.setInt(4, allocation.getAllocationId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating allocation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete an allocation by ID
     * @param allocationId ID of the allocation to delete
     * @return true if deletion is successful, false otherwise
     */
    public boolean deleteAllocation(int allocationId) {
        String sql = "DELETE FROM allocations WHERE allocationId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, allocationId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting allocation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
