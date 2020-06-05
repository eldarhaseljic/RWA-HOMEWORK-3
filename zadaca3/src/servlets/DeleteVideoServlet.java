package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VideoDao;
import service.VideoService;

/**
 * Servlet implementation class DeleteVideoServlet
 */
@WebServlet("/admin/delete")
public class DeleteVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	VideoService videoService = new VideoService(new VideoDao());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVideoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		videoService.delete(request.getParameter("title"));
		response.sendRedirect("/zadaca3/admin/main");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}
