package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/admin/user/update")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserService(new UserDao());
	User user = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		user = userService.getUserByUserName(request.getParameter("userName"));
		out.println("<html>"
					+ "<head>"
						+ "<title>Edit</title>"
						+ "<link rel='stylesheet' type='text/css' href='/zadaca3/css/admin.css'>"
						+ "<link rel='stylesheet' type='text/css'"
							+ " href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>"
					+ "</head>"
					+ "<body>"
						+ "<h1 align='center'>Update Users</h1>"
							+ "<form action='/zadaca3/admin/user/update' method='post'>"
								+ "<table style='width:50%' align='center'>"
									+ "<tr>"
										+ "<td>"
											+ "<div align='center' class='text'>UserName</div>"
										+ "</td>"
										+ "<td align='center'>"
											+ "<div class='ui input' style='width:90%'>"
												+ "<input type='text' name='userName' value='" + user.getUserName() + "'>"
											+ "</div>"
										+ "</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>"
											+ "<div  align='center' class='text'>Password </div>"
										+ "</td>"
										+ "<td align='center'>"
											+ "<div class='ui input' style='width:90%'>"
												+ "<input type='text' name='password' value='" + user.getPassword() + "'>"
											+ "</div>"
										+ "</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>"
											+ "<div  align='center' class='text'>Role (for more roles just separate them with ',')</div>"
										+ "</td>"
										+ "<td align='center'>"
											+ "<div class='ui input' style='width:90%'>"
												+ "<input type='text' name='roles' value='" + user.getRolesString() + "'>"
											+ "</div>"
										+ "</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td colspan='2' align='center'>"
											+ "<button class='btn1' type='submit'>Update</button>"
										+ "</td>"
									+"</tr>"
								+ "</table>"
							+ "</form>"
								+ "<div align='center'>"
									+ "<a href='/zadaca3/admin/user'>" 
										+ "<button class='btn1'>Cancel</button>"			
									+ "</a>"
								+ "</div>"
							+ "</body>"
						+ "</html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String roles = request.getParameter("roles");
		if(userName.isEmpty() || password.isEmpty() || roles.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Fields can not be empty");
			response.sendRedirect("/zadaca3/admin/user/update?userName="+user.getUserName());
		}
		else 
		{
			if(!userName.equals(user.getUserName()))
			{
				User temp = userService.getUserByUserName(userName);
				if(temp != null)
				{
					JOptionPane.showMessageDialog(null, "The user name is already taken");
					response.sendRedirect("/zadaca3/admin/user/update?userName="+user.getUserName());
				}
				else {
					User updateUser = new User(userName, password, roles);
					userService.updateUser(user, updateUser);
					response.sendRedirect("/zadaca3/admin/user");
				}
			}
			else 
			{
				User updateUser = new User(userName, password, roles);
				userService.updateUser(user, updateUser);
				response.sendRedirect("/zadaca3/admin/user");
			}
		}
	}

}
