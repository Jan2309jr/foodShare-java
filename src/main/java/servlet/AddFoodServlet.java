package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException; // Specific import
import jakarta.servlet.annotation.WebServlet; // Needed for the URL mapping
import jakarta.servlet.http.*;
import java.sql.*;
import util.DBConnection;
import model.User; // Import your User class

@WebServlet("/AddFoodServlet") // Don't forget the annotation!
public class AddFoodServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        // Safety check: if user is not logged in, redirect to login
        if (user == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        int donorId = user.getId();

        try (Connection con = DBConnection.getConnection()) { // Using try-with-resources to auto-close connection

        	String query = "INSERT INTO food_listings (donor_id, food_name, quantity, location, expiry) VALUES (?,?,?,?,?)";PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, donorId);
            ps.setString(2, req.getParameter("food"));
            ps.setString(3, req.getParameter("quantity"));
            ps.setString(4, req.getParameter("location"));
            ps.setString(5, req.getParameter("expiry"));

            ps.executeUpdate();
            res.sendRedirect("allFood.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("Error: " + e.getMessage());
        }
    }
}