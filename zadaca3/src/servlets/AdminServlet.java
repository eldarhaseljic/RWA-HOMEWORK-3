package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VideoDao;
import domain.Video;
import service.VideoService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin/main")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	VideoService videoService = new VideoService(new VideoDao());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		PrintWriter out = response.getWriter();
		List<Video> videos = videoService.findAll();
		
		out.println("<html> "
					+ "<head>" 
						+ "<meta charset='UTF-8'>"
						+ "<meta name='viewport' content='width=device-width, initial-scale=1.0 maximum-scale=1'>"
						+ "<link rel='stylesheet' type='text/css' href='/zadaca3/css/admin.css'>"
						+ "<link rel='stylesheet' type='text/css' "
							+ "href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>"
						+ "<title>Admin main page</title>" 
					+ "</head>" 
					+ "<body> " 
					+ "<h1 align='center'>Admin side of the server</h1>"
						+ "<div align='center'>"
							+ "<a href='/zadaca3/admin/user'>" 
								+ "<button class='btn1'>" + "Users" + "</button>" 
							+ "</a>"
							+ "<a href='/zadaca3/admin/newVideo'>" 
								+ "<button  class='btn1'>" + "Add new video" + "</button>" 
							+ "</a>"
							+ "<a href='/zadaca3/admin/logout'>" 
								+ "<button  class='btn1'>" + "Logout" + "</button>" 
							+ "</a>"
						+ "</div>" 
						+ "<div id='videosFromTheDataBase' align='center'>"
						+ "<table id='dataBase' border='4' bordercolor='lightslategray'>" 
							+ "<tr>" 
								+ "<th>Rank</th>" 
								+ "<th>Video ID</th>"
								+ "<th>Name</th>" 
								+ "<th>Positive Votes</th>" 
								+ "<th>Total Votes</th>" 
								+ "<th>Image</th>" 
								+ "<th>Edit</th>" 
								+ "<th>Delete</th>" 
							+ "</tr>");
		for (Integer i = 0; i< videos.size(); ++i) {
			Video v = videos.get(i);
			out.print("<tr>" 
						+ "<td>" 
							+ "<div align='center' class='text'>" + Integer.toString(i+1) + "</div>" 
						+ "</td>"
						+ "<td>" 
							+ "<div align='center' class='text'>" + v.getVideoId() + "</div>" 
						+ "</td>"
						+ "<td>" 
							+ "<div align='center' class='text'>" + v.getVideoName() + "</div>" 
						+ "</td>" 
						+ "<td>" 
							+ "<div align='center' class='text'>" + Integer.toString(v.getPositiveVotes()) + "</div>" 
						+ "</td>"
						+ "<td>" 
							+ "<div align='center' class='text'>" + Integer.toString(v.getTotalVotes()) + "</div>" 
						+ "</td>"
						+ "<td>"
							+ "<div align='center' class='text2'>" 
								+ "<img src='" + v.getImage() + "' class='portrait'/>"
							+ "</div>" 
						+ "</td>"
						+ "<td>"
							+ "<a href='/zadaca3/admin/edit?title=" + v.getVideoName()  + "' class='littleIcons1'>"
								+ "<i class='edit icon portrait'></i>"
							+ "</a>" 
						+ "</td>" 
						+ "<td>" 
							+ "<a href='/zadaca3/admin/delete?title=" + v.getVideoName() + "' class='littleIcons1'>"
								+ "<i class='trash icon portrait'></i>"
							+ "</a>"
						+ "</td>"
					+ "</tr>");
		}
		out.print("</table>" + "</div>" + "</body>" + "</html>");
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
