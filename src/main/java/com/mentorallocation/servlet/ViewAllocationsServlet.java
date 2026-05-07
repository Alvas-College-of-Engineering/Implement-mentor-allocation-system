package com.mentorallocation.servlet;

import com.mentorallocation.dao.AllocationDAO;
import com.mentorallocation.dao.MentorDAO;
import com.mentorallocation.dao.StudentDAO;
import com.mentorallocation.model.Allocation;
import com.mentorallocation.model.Mentor;
import com.mentorallocation.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ViewAllocationsServlet
 * Handles HTTP requests to view all mentor-student allocations
 * Displays allocation records with mentor and student details
 */
@WebServlet("/viewAllocations")
public class ViewAllocationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AllocationDAO allocationDAO;
    private MentorDAO mentorDAO;
    private StudentDAO studentDAO;

    /**
     * Initialize servlet
     */
    @Override
    public void init() throws ServletException {
        super.init();
        allocationDAO = new AllocationDAO();
        mentorDAO = new MentorDAO();
        studentDAO = new StudentDAO();
    }

    /**
     * Handle GET requests - Retrieve and display all allocations
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve all allocations from database
            List<Allocation> allocations = allocationDAO.getAllAllocations();

            // Create a list to hold allocation details with mentor and student names
            List<AllocationDetail> allocationDetails = new ArrayList<>();

            // Populate allocation details
            for (Allocation allocation : allocations) {
                Mentor mentor = mentorDAO.getMentorById(allocation.getMentorId());
                Student student = studentDAO.getStudentById(allocation.getStudentId());

                if (mentor != null && student != null) {
                    allocationDetails.add(new AllocationDetail(
                            allocation.getAllocationId(),
                            mentor.getName(),
                            student.getName(),
                            mentor.getSpecialization(),
                            allocation.getAllocationDate()
                    ));
                }
            }

            // Set allocations as request attribute
            request.setAttribute("allocations", allocationDetails);

            // Forward to viewAllocations.jsp
            request.getRequestDispatcher("viewAllocations.jsp").forward(request, response);

        } catch (Exception e) {
            System.err.println("Error in ViewAllocationsServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "Failed to retrieve allocations: " + e.getMessage());
            request.getRequestDispatcher("viewAllocations.jsp").forward(request, response);
        }
    }

    /**
     * Inner class to hold allocation details for display
     */
    public static class AllocationDetail {
        private int allocationId;
        private String mentorName;
        private String studentName;
        private String specialization;
        private java.time.LocalDate allocationDate;

        public AllocationDetail(int allocationId, String mentorName, String studentName,
                              String specialization, java.time.LocalDate allocationDate) {
            this.allocationId = allocationId;
            this.mentorName = mentorName;
            this.studentName = studentName;
            this.specialization = specialization;
            this.allocationDate = allocationDate;
        }

        // Getters
        public int getAllocationId() {
            return allocationId;
        }

        public String getMentorName() {
            return mentorName;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getSpecialization() {
            return specialization;
        }

        public java.time.LocalDate getAllocationDate() {
            return allocationDate;
        }
    }
}
