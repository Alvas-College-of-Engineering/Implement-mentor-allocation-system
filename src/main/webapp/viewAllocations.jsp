<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.mentorallocation.servlet.ViewAllocationsServlet.AllocationDetail" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Allocations - Mentor Allocation System</title>
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
            max-width: 900px;
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

        .error-message {
            background: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }

        .info-message {
            background: #d1ecf1;
            color: #0c5460;
            border: 1px solid #bee5eb;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table thead {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }

        table th {
            padding: 15px;
            text-align: left;
            font-weight: bold;
            border: 1px solid #ddd;
        }

        table td {
            padding: 15px;
            border: 1px solid #ddd;
        }

        table tbody tr:nth-child(even) {
            background: #f9f9f9;
        }

        table tbody tr:hover {
            background: #f0f4ff;
            transition: background 0.3s;
        }

        .allocation-id {
            font-weight: bold;
            color: #667eea;
        }

        .specialization {
            background: #e7f3ff;
            color: #1565c0;
            padding: 5px 10px;
            border-radius: 20px;
            display: inline-block;
            font-size: 0.9em;
        }

        .date {
            color: #666;
            font-size: 0.95em;
        }

        .empty-state {
            text-align: center;
            padding: 40px 20px;
            color: #666;
        }

        .empty-state-icon {
            font-size: 3em;
            margin-bottom: 10px;
        }

        @media (max-width: 900px) {
            .container {
                padding: 20px;
            }

            table {
                font-size: 0.9em;
            }

            table th, table td {
                padding: 10px;
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
            <h1>View All Allocations</h1>
            <a href="index.jsp" class="back-link">← Back to Home</a>
        </div>

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

        <%
            List<AllocationDetail> allocations = (List<AllocationDetail>) request.getAttribute("allocations");
            if (allocations != null && !allocations.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Allocation ID</th>
                        <th>Mentor Name</th>
                        <th>Student Name</th>
                        <th>Specialization</th>
                        <th>Allocation Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (AllocationDetail allocation : allocations) {
                    %>
                        <tr>
                            <td class="allocation-id">#<%= allocation.getAllocationId() %></td>
                            <td><strong><%= allocation.getMentorName() %></strong></td>
                            <td><strong><%= allocation.getStudentName() %></strong></td>
                            <td><span class="specialization"><%= allocation.getSpecialization() %></span></td>
                            <td class="date"><%= allocation.getAllocationDate() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <div class="empty-state">
                <div class="empty-state-icon">📋</div>
                <h2>No Allocations Found</h2>
                <p>There are currently no mentor-student allocations. Start by adding mentors and students!</p>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
