<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Food - FoodShare</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>Edit Food Listing</h2>
        <%
            // Get the ID from the URL
            String foodId = request.getParameter("id");
        %>
        <form action="FoodActionServlet" method="post">
            <input type="hidden" name="id" value="<%= foodId %>">
            <input type="hidden" name="action" value="update">

            <label>Food Item</label>
            <input type="text" name="food_name" placeholder="New name" required>
            
            <label>Quantity</label>
            <input type="text" name="quantity" placeholder="New quantity" required>
            
            <label>Location</label>
            <input type="text" name="location" placeholder="New location" required>

            <button type="submit">Update Listing</button>
            <p style="text-align: center;"><a href="allFood.jsp">Cancel</a></p>
        </form>
    </div>
</body>
</html>