package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import domain.User;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestDispatcher dispatcher = null;
	UserService userService = new UserService(new UserDao());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String userName = request.getParameter("email");
		String password = request.getParameter("password");

		User user = userService.getUserByUserName(userName);
		if (user == null) {
			String error = "Invalid email or user name";

			request.setAttribute("error", error);
			dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);

			return;
		} else {
			user = userService.getUser(userName, password);

			if (user == null) {
				String error = "Invalid password";
				request.setAttribute("error", error);
				dispatcher = request.getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
				return;
			}

			List<String> roles = user.getRoles();
			if (!user.isAdmin(roles)) {
				response.sendRedirect("/zadaca3");
			} else {
				request.getSession().setAttribute("user", user);
				response.sendRedirect("/zadaca3/admin/main");
			}
		}
	}
}
