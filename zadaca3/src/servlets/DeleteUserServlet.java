package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dao.UserDao;
import domain.User;
import service.UserService;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/admin/user/delete")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService(new UserDao());
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		User userinSesion = (User) request.getSession().getAttribute("user");
		if(userinSesion.getUserName().equals(request.getParameter("userName")))
		{
			JOptionPane.showMessageDialog(null, "You can not delete yourself");
			response.sendRedirect("/zadaca3/admin/user");
		}
		else{
			userService.deleteUser(request.getParameter("userName"));
			response.sendRedirect("/zadaca3/admin/user");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
