<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Join FoodShare</title>
</head>
<body class="auth-page">
    <div class="auth-card">
        <div class="auth-header">
            <h2>Create Account</h2>
            <p>Join the community to start sharing food.</p>
        </div>
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label>Full Name</label>
                <input type="text" name="name" placeholder="Enter your name" required>
            </div>
            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email" placeholder="name@example.com" required>
            </div>
            <div class="form-group">
                <label>Phone Number</label>
                <input type="text" name="phone" placeholder="919876543210" required>
            </div>
            <div class="form-group">
                <label>Account Role</label>
                <select name="role">
                    <option value="donor">I want to Donate Food</option>
                    <option value="receiver">I want to Receive Food</option>
                </select>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="Min. 8 characters" required>
            </div>
            <button type="submit" class="btn-submit">Register</button>
        </form>
        <div class="auth-footer">
            <p>Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
</body>
</html>