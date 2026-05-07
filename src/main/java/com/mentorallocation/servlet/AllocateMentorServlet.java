package com.mentorallocation.servlet;

import com.mentorallocation.service.AllocationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * AllocateMentorServlet
 * Handles HTTP requests to allocate mentors to students
 * Applies business rules for allocation (specialization match, availability)
 */
@WebServlet("/allocateMentor")
public class AllocateMentorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AllocationService allocationService;

    /**
     * Initialize servlet
     */
    @Override
    public void init() throws ServletException {
        super.init();
        allocationService = new AllocationService();
    }

    /**
     * Handle GET requests - Display allocation form
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to allocateMentor.jsp
        request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
    }

    /**
     * Handle POST requests - Process allocation request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get form parameters
            String mentorIdStr = request.getParameter("mentorId");
            String studentIdStr = request.getParameter("studentId");

            // Validate input
            if (mentorIdStr == null || mentorIdStr.trim().isEmpty() ||
                studentIdStr == null || studentIdStr.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Please select both mentor and student!");
                request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
                return;
            }

            try {
                int mentorId = Integer.parseInt(mentorIdStr);
                int studentId = Integer.parseInt(studentIdStr);

                // Check if allocation is possible
                if (!allocationService.canAllocate(mentorId, studentId)) {
                    request.setAttribute("errorMessage",
                            "Cannot allocate: Mentor specialization may not match student requirements " +
                            "or mentor is not available!");
                    request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
                    return;
                }

                // Attempt to allocate mentor
                if (allocationService.allocateMentor(mentorId, studentId) != null) {
                    request.setAttribute("successMessage",
                            "Mentor allocated successfully to the student!");
                    request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Failed to allocate mentor. Please try again!");
                    request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
                }

            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid mentor or student ID!");
                request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.err.println("Error in AllocateMentorServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("allocateMentor.jsp").forward(request, response);
        }
    }
}
