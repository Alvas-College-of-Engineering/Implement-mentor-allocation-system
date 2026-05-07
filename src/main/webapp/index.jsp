<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mentor Allocation System</title>
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
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        .container {
            background: white;
            border-radius: 10px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            padding: 50px;
            max-width: 600px;
            width: 100%;
            text-align: center;
        }

        h1 {
            color: #333;
            margin-bottom: 20px;
            font-size: 2.5em;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }

        .subtitle {
            color: #666;
            margin-bottom: 40px;
            font-size: 1.1em;
        }

        .nav-buttons {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            margin-bottom: 30px;
        }

        .nav-btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px 30px;
            border: none;
            border-radius: 5px;
            font-size: 1em;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            transition: transform 0.3s, box-shadow 0.3s;
            font-weight: bold;
        }

        .nav-btn:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 20px rgba(0, 0, 0, 0.3);
        }

        .nav-btn.secondary {
            background: linear-gradient(135deg, #f093fb 0%, #f5576c 100%);
        }

        .info-box {
            background: #f0f4ff;
            border-left: 4px solid #667eea;
            padding: 20px;
            margin-top: 30px;
            border-radius: 5px;
            text-align: left;
        }

        .info-box h3 {
            color: #667eea;
            margin-bottom: 10px;
        }

        .info-box p {
            color: #555;
            line-height: 1.6;
        }

        @media (max-width: 600px) {
            .container {
                padding: 30px 20px;
            }

            h1 {
                font-size: 1.8em;
            }

            .nav-buttons {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>🎓 Mentor Allocation System</h1>
        <p class="subtitle">Manage mentor-student allocations effectively</p>

        <div class="nav-buttons">
            <a href="addMentor" class="nav-btn">Add Mentor</a>
            <a href="addStudent" class="nav-btn secondary">Add Student</a>
            <a href="allocateMentor" class="nav-btn">Allocate Mentor</a>
            <a href="viewAllocations" class="nav-btn secondary">View Allocations</a>
        </div>

        <div class="info-box">
            <h3>📌 System Features</h3>
            <p>
                • Add new mentors with specialization<br>
                • Register students with required specialization<br>
                • Allocate mentors based on specialization match<br>
                • View all allocation records<br>
                • Automatic availability management
            </p>
        </div>
    </div>
</body>
</html>
