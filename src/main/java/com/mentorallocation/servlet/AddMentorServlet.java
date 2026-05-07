package com.mentorallocation.servlet;

import com.mentorallocation.dao.MentorDAO;
import com.mentorallocation.model.Mentor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * AddMentorServlet
 * Handles HTTP requests to add a new mentor to the system
 */
@WebServlet("/addMentor")
public class AddMentorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MentorDAO mentorDAO;

    /**
     * Initialize servlet
     */
    @Override
    public void init() throws ServletException {
        super.init();
        mentorDAO = new MentorDAO();
    }

    /**
     * Handle GET requests - Display add mentor form
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to addMentor.jsp
        request.getRequestDispatcher("addMentor.jsp").forward(request, response);
    }

    /**
     * Handle POST requests - Process form submission
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Get form parameters
            String name = request.getParameter("name");
            String specialization = request.getParameter("specialization");
            String availabilityStr = request.getParameter("availability");

            // Validate input
            if (name == null || name.trim().isEmpty() ||
                specialization == null || specialization.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Please fill all required fields!");
                request.getRequestDispatcher("addMentor.jsp").forward(request, response);
                return;
            }

            // Set availability - default to true if checkbox is checked
            boolean availability = "on".equals(availabilityStr);

            // Create new mentor object
            Mentor mentor = new Mentor(name.trim(), specialization.trim(), availability);

            // Insert mentor into database
            if (mentorDAO.insertMentor(mentor)) {
                request.setAttribute("successMessage", "Mentor added successfully!");
                request.getRequestDispatcher("addMentor.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to add mentor. Please try again!");
                request.getRequestDispatcher("addMentor.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.err.println("Error in AddMentorServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("addMentor.jsp").forward(request, response);
        }
    }
}
