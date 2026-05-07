package com.mentorallocation.servlet;

import com.mentorallocation.dao.StudentDAO;
import com.mentorallocation.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * AddStudentServlet
 * Handles HTTP requests to add a new student to the system
 */
@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    /**
     * Initialize servlet
     */
    @Override
    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAO();
    }

    /**
     * Handle GET requests - Display add student form
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to addStudent.jsp
        request.getRequestDispatcher("addStudent.jsp").forward(request, response);
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
            String department = request.getParameter("department");
            String requiredSpecialization = request.getParameter("requiredSpecialization");

            // Validate input
            if (name == null || name.trim().isEmpty() ||
                department == null || department.trim().isEmpty() ||
                requiredSpecialization == null || requiredSpecialization.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Please fill all required fields!");
                request.getRequestDispatcher("addStudent.jsp").forward(request, response);
                return;
            }

            // Create new student object
            Student student = new Student(
                    name.trim(),
                    department.trim(),
                    requiredSpecialization.trim()
            );

            // Insert student into database
            if (studentDAO.insertStudent(student)) {
                request.setAttribute("successMessage", "Student added successfully!");
                request.getRequestDispatcher("addStudent.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to add student. Please try again!");
                request.getRequestDispatcher("addStudent.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.err.println("Error in AddStudentServlet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("addStudent.jsp").forward(request, response);
        }
    }
}
