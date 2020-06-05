package servlets;

import java.awt.Window;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class NewUserServlet
 */
@WebServlet("/admin/user/newUser")
public class NewUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserService userService = new UserService(new UserDao());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewUserServlet() {
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
		PrintWriter out = response.getWriter();
		out.println("<html>"
					+ "<head>"
						+"<title>New User Page</title>"
						+"<link rel='stylesheet' type='text/css' href='/zadaca3/css/admin.css'>"
						+"<link rel='stylesheet' type='text/css' "
							+ "href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>"
					+"</head>"
					+ "<body>"
						+ "<h1 align='center' >Add New User</h1>"
							+ "<form action='/zadaca3/admin/user/newUser' method='post'>"
								+ "<table style='width:50%' align='center'>"
									+ "<tr>"
										+ "<td>"
											+ "<div align='center' class='text'>UserName</div>"
										+ "</td>"
										+ "<td align='center'>"
											+ "<div class='ui input' style='width:90%'>"
												+ "<input type='text' name='userName' value=''>"
											+ "</div>"
										+ "</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>"
											+ "<div  align='center' class='text'>Password</div>"
										+ "</td>"
										+ "<td align='center'>"
											+ "<div class='ui input' style='width:90%'>"
												+ "<input type='text' name='password' value=''>"
											+ "</div>"
										+ "</td>"
									+ "</tr>"
									+ "<tr>"
										+ "<td>"
											+ "<div  align='center' class='text'>Role (for more roles just separate them with ',')</div>"
										+ "</td>"
										+ "<td align='center'>"
											+ "<div class='ui input' style='width:90%'>"
												+ "<input type='text' name='roles' value=''>"
											+ "</div>"
										+ "</td>"
									+ "</tr>"
									+"<tr>"
										+ "<td colspan='2' align='center'>"
											+ "<button class='btn1' type='submit'>Save</button>"
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
			JOptionPane.showMessageDialog(null, "Please fill all fields");
			response.sendRedirect("/zadaca3/admin/user/newUser");
		}
		else 
		{
			if(userService.getUserByUserName(userName) != null)
			{
				JOptionPane.showMessageDialog(null, "There is already a user with that name");
				response.sendRedirect("/zadaca3/admin/user");
			}
			else 
			{
				userService.saveUser(new User(userName,password,roles));
				response.sendRedirect("/zadaca3/admin/user");
			}
		}
	}

}
