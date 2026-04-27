<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, dao.UserDAO, model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Food - FoodShare</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container" style="max-width: 1100px;">
        <div class="header-section">
            <h2>Available Food Donations</h2>
            <p>Help reduce waste by availing surplus food near you.</p>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>Food Item</th>
                    <th>Quantity</th>
                    <th>Location</th>
                    <th>Donor</th>
                    <th style="text-align: center;">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    User currentUser = (User) session.getAttribute("user");
                    UserDAO dao = new UserDAO();
                    List<Map<String, String>> list = dao.getAllFood();
                    
                    if(list == null || list.isEmpty()) {
                %>
                    <tr><td colspan="5" style="text-align:center;">No food donations available.</td></tr>
                <%
                    } else {
                        for(Map<String, String> item : list) {
                            String foodName = item.get("food_name");
                            String donorPhone = item.get("phone");
                            String foodId = item.get("id");
                            int donorId = Integer.parseInt(item.get("donor_id"));
                            
                            String cleanPhone = (donorPhone != null) ? donorPhone.replaceAll("[^0-9]", "") : "";
                            if (!cleanPhone.isEmpty() && !cleanPhone.startsWith("91")) { cleanPhone = "91" + cleanPhone; }
                            
                            String msg = "Hello, I am interested in your food donation: " + foodName;
                            String encodedMsg = java.net.URLEncoder.encode(msg, "UTF-8");
                            String waLink = "https://api.whatsapp.com/send?phone=" + cleanPhone + "&text=" + encodedMsg;
                %>
                <tr>
                    <td class="food-name-cell"><%= foodName %></td>
                    <td><span class="badge"><%= item.get("quantity") %></span></td>
                    <td><i class="loc-icon">📍</i> <%= item.get("location") %></td>
                    <td><strong><%= item.get("donor_name") %></strong></td>
                    <td style="text-align: center;">
                        <div class="action-group">
                            <a href="<%= waLink %>" target="_blank" class="wa-btn">Avail</a>

                            <% if(currentUser != null && currentUser.getId() == donorId) { %>
                                <a href="editFood.jsp?id=<%= foodId %>" class="btn-edit">Edit</a>
                                <a href="FoodActionServlet?action=delete&id=<%= foodId %>" 
                                   class="btn-delete"
                                   onclick="return confirm('Are you sure you want to delete this?')">Delete</a>
                            <% } %>
                        </div>
                    </td>
                </tr>
                <% 
                        } 
                    } 
                %>
            </tbody>
        </table>
        
        <div class="footer-nav">
            <a href="donor.jsp" class="nav-link"> Add New Donation</a>
            <span class="sep">|</span>
            <a href="index.jsp" class="nav-link"> Home</a>
        </div>
    </div>
</body>
</html>