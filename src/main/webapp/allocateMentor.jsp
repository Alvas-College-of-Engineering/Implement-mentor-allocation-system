<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mentorallocation.dao.MentorDAO" %>
<%@ page import="com.mentorallocation.dao.StudentDAO" %>
<%@ page import="com.mentorallocation.model.Mentor" %>
<%@ page import="com.mentorallocation.model.Student" %>
<%@ page import="java.util.List" %>
<%
    MentorDAO mentorDAO = new MentorDAO();
    StudentDAO studentDAO = new StudentDAO();
    List<Mentor> mentors = mentorDAO.getAllMentors();
    List<Student> students = studentDAO.getAllStudents();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Allocate Mentor - Mentor Allocation System</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            padding: 20px;
        }

        .container {
            max-width: 600px;
            margin: 40px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            border-bottom: 2px solid #667eea;
            padding-bottom: 15px;
        }

        h1 {
            color: #333;
            font-size: 1.8em;
        }

        .back-link {
            background: #667eea;
            color: white;
            padding: 8px 16px;
            border-radius: 5px;
            text-decoration: none;
            font-size: 0.9em;
            transition: background 0.3s;
        }

        .back-link:hover {
            background: #764ba2;
        }

        .message {
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            font-weight: bold;
        }

        .success-message {
            background: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }

        .error-message {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
            font-weight: bold;
        }

        input[type="number"],
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1em;
            transition: border-color 0.3s;
        }

        input[type="number"]:focus,
        select:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
        }

        button {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 12px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            font-weight: bold;
            transition: transform 0.3s, box-shadow 0.3s;
        }

        button:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
        }

        .required {
            color: #f5576c;
        }

        .info-text {
            background: #e7f3ff;
            border-left: 4px solid #2196F3;
            padding: 12px;
            margin-bottom: 20px;
            border-radius: 4px;
            font-size: 0.95em;
            color: #1565c0;
        }

        @media (max-width: 600px) {
            .container {
                padding: 20px;
            }

            .header {
                flex-direction: column;
                gap: 15px;
                text-align: center;
            }

            h1 {
                font-size: 1.5em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Allocate Mentor</h1>
            <a href="index.jsp" class="back-link">← Back to Home</a>
        </div>

        <!-- Display success message if set -->
        <%
            String successMessage = (String) request.getAttribute("successMessage");
            if (successMessage != null) {
        %>
            <div class="message success-message">
                ✓ <%= successMessage %>
            </div>
        <%
            }
        %>

        <!-- Display error message if set -->
        <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
        %>
            <div class="message error-message">
                ✗ <%= errorMessage %>
            </div>
        <%
            }
        %>

        <div class="info-text">
            💡 Select an available mentor and student. The mentor's specialization must match the student's required specialization.
        </div>

        <form method="POST" action="allocateMentor">
            <div class="form-group">
                <label for="mentorId">
                    Available Mentor <span class="required">*</span>
                </label>
                <select id="mentorId" name="mentorId" required>
                    <option value="">-- Select Mentor --</option>
                    <%
                        for (Mentor mentor : mentors) {
                    %>
                        <option value="<%= mentor.getMentorId() %>"><%= mentor.getName() %> - <%= mentor.getSpecialization() %> (<%= mentor.isAvailability() ? "Available" : "Unavailable" %>)</option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="studentId">
                    Student <span class="required">*</span>
                </label>
                <select id="studentId" name="studentId" required>
                    <option value="">-- Select Student --</option>
                    <%
                        for (Student student : students) {
                    %>
                        <option value="<%= student.getStudentId() %>"><%= student.getName() %> - <%= student.getDepartment() %> (<%= student.getRequiredSpecialization() %>)</option>
                    <%
                        }
                    %>
                </select>
            </div>

            <button type="submit">Allocate Mentor</button>
        </form>
    </div>
</body>
</html>
