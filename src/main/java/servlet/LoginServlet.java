package servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UserDAO;
import jakarta.servlet.http.*;
import model.User;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if(user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            if(user.getRole().equals("donor"))
                res.sendRedirect("donor.jsp");
            else
            	res.sendRedirect("allFood.jsp");

        } else {
            res.getWriter().println("Invalid credentials");
        }
    }
}