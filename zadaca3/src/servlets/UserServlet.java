package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import domain.User;
import service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/admin/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService(new UserDao());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
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

		response.setContentType("text/html");
		List<User> allUsers = userService.getAllUsers();
		PrintWriter out = response.getWriter();
		out.println("<html>" 
					+ "<head>" 
						+ "<title>Users</title>"
						+ "<link rel='stylesheet' type='text/css' href='/zadaca3/css/admin.css'>"
						+ "<link rel='stylesheet' type='text/css' "
							+ "href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>" 
					+ "</head>" 
					+ "<body>"
					+ "<h1 align='center'>Users</h1>"
						+ "<div id='header-buttons' align='center'>" 
							+ "<a href='/zadaca3/admin/main'>" 
								+ "<button class='btn1''>Back</button>"
							+ "</a>" 
							+ "<a href='/zadaca3/admin/user/newUser'>" 
								+ "<button class='btn1'>Add new user</button>"
							+ "</a>"
						+ "</div>" 
						+ "<div id='videosFromTheDataBase' align='center'>"
						+ "<table id='dataBase' border='4' bordercolor='lightslategray'>" 
							+ "<tr>" 
								+ "<th>User Name</th>"
								+ "<th>Password</th>" 
								+ "<th>Roles</th>" 
								+ "<th>Edit</th>" 
								+ "<th>Delete</th>" 
							+ "</tr>");
		for (User user : allUsers) {
			out.print("<tr>" 
						+ "<td>" 
							+ "<div align='center' class='text'>" + user.getUserName() + "</div>"
						+ "</td>"
						+ "<td>" 
							+ "<div align='center' class='text'>" + user.getPassword() + "</div>" 
						+ "</td>" 
						+ "<td>"
							+ "<div align='center' class='text'>" + user.getRolesString() + "</div>" 
						+ "</td>" 
						+ "<td>"
							+ "<a href='/zadaca3/admin/user/update?userName=" + user.getUserName() + "'class='littleIcons'>"
								+ "<i class='edit icon portrait'></i>" 
							+ "</a>" 
						+ "</td>"
						+ "<td>"
							+ "<a href='/zadaca3/admin/user/delete?userName=" + user.getUserName() + "' class='littleIcons'>"
								+ "<i class='trash icon portrait'></i>" 
							+ "</a>" 
						+ "</td>"
					+ "</tr>");
		}
		out.print("</table>"
				+ "</div>"
			+ "</body>"
		+ "</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
