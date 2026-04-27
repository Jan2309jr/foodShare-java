<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Login | FoodShare</title>
</head>
<body class="auth-page">
    <div class="auth-card">
        <div class="auth-header">
            <h2>Welcome Back</h2>
            <p>Login to manage your food donations.</p>
        </div>
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email" placeholder="name@example.com" required>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" placeholder="••••••••" required>
            </div>
            <button type="submit" class="btn-submit">Login</button>
        </form>
        <div class="auth-footer">
            <p>New to FoodShare? <a href="register.jsp">Create an account</a></p>
        </div>
    </div>
</body>
</html>