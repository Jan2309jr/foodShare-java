package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.UserDAO;

/**
 * Servlet implementation class FoodAction
 */
@WebServlet("/FoodActionServlet")
public class FoodActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoodActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));
        UserDAO dao = new UserDAO();

        if ("delete".equals(action)) {
            dao.deleteFood(id);
        }
        res.sendRedirect("allFood.jsp");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");
        int id = Integer.parseInt(req.getParameter("id"));
        UserDAO dao = new UserDAO();

        if ("update".equals(action)) {
            String name = req.getParameter("food_name");
            String qty = req.getParameter("quantity");
            String loc = req.getParameter("location");

            if(dao.updateFood(id, name, qty, loc)) {
                res.sendRedirect("allFood.jsp");
            } else {
                res.getWriter().println("Update failed.");
            }
        }
    }
}
