package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Importing from the 'dao' and 'model' packages
import dao.UserDAO;
import model.User; 

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // 1. Collect data from the JSP form
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String pass = req.getParameter("password");
        String role = req.getParameter("role");
        
        // --- NEW: Collect the phone number ---
        String phone = req.getParameter("phone");

        // 2. Populate the User model
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(pass);
        user.setRole(role);
        
        // --- NEW: Set the phone number in the model ---
        user.setPhone(phone);

        // 3. Use the DAO to save to Database
        UserDAO dao = new UserDAO();

        try {
            // This will now use the updated UserDAO method that includes the phone column
            if(dao.register(user)) {
                // Success: Go to login page
                res.sendRedirect("login.jsp");
            } else {
                // Failure: Stay on register page or show error
                res.getWriter().println("Registration Failed: Database error. Check Eclipse Console for details.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            res.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}