package com.mentorallocation.service;

import com.mentorallocation.dao.AllocationDAO;
import com.mentorallocation.dao.MentorDAO;
import com.mentorallocation.dao.StudentDAO;
import com.mentorallocation.model.Allocation;
import com.mentorallocation.model.Mentor;
import com.mentorallocation.model.Student;

import java.time.LocalDate;

/**
 * AllocationService Class
 * Handles business logic for mentor-student allocation
 * Validates allocation conditions before creating allocations
 */
public class AllocationService {
    private MentorDAO mentorDAO;
    private StudentDAO studentDAO;
    private AllocationDAO allocationDAO;

    /**
     * Constructor initializing DAO instances
     */
    public AllocationService() {
        this.mentorDAO = new MentorDAO();
        this.studentDAO = new StudentDAO();
        this.allocationDAO = new AllocationDAO();
    }

    /**
     * Allocate a mentor to a student with validation
     * 
     * Allocation Rules:
     * 1. Mentor specialization must match student's required specialization
     * 2. Mentor must be available (availability = true)
     * 3. After successful allocation, mentor's availability is set to false
     * 
     * @param mentorId ID of the mentor
     * @param studentId ID of the student
     * @return Allocation object if successful, null if allocation fails
     */
    public Allocation allocateMentor(int mentorId, int studentId) {
        try {
            // Fetch mentor and student
            Mentor mentor = mentorDAO.getMentorById(mentorId);
            Student student = studentDAO.getStudentById(studentId);

            // Validate mentor and student exist
            if (mentor == null || student == null) {
                System.err.println("Mentor or Student not found!");
                return null;
            }

            // Validate mentor specialization matches student's required specialization
            if (!mentor.getSpecialization().equalsIgnoreCase(student.getRequiredSpecialization())) {
                System.err.println("Mentor specialization does not match student's required specialization!");
                return null;
            }

            // Validate mentor is available
            if (!mentor.isAvailability()) {
                System.err.println("Mentor is not available for allocation!");
                return null;
            }

            // Create allocation record
            Allocation allocation = new Allocation(
                    mentorId,
                    studentId,
                    LocalDate.now()
            );

            // Insert allocation into database
            if (allocationDAO.insertAllocation(allocation)) {
                // Update mentor's availability to false
                mentorDAO.updateMentorAvailability(mentorId, false);

                System.out.println("Mentor allocated successfully!");
                return allocation;
            } else {
                System.err.println("Failed to create allocation record!");
                return null;
            }

        } catch (Exception e) {
            System.err.println("Error during allocation: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Check if mentor can be allocated to student
     * @param mentorId ID of the mentor
     * @param studentId ID of the student
     * @return true if allocation is possible, false otherwise
     */
    public boolean canAllocate(int mentorId, int studentId) {
        Mentor mentor = mentorDAO.getMentorById(mentorId);
        Student student = studentDAO.getStudentById(studentId);

        if (mentor == null || student == null) {
            return false;
        }

        // Check specialization match and availability
        return mentor.getSpecialization().equalsIgnoreCase(student.getRequiredSpecialization())
                && mentor.isAvailability();
    }
}
