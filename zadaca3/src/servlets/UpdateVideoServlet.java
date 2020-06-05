package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import dao.VideoDao;
import domain.Video;
import service.VideoService;

/**
 * Servlet implementation class UpdateVideoServlet
 */
@WebServlet("/admin/edit")
public class UpdateVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	VideoService videoService = new VideoService(new VideoDao());
	Video video = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVideoServlet() {
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
		System.out.println(request.getParameter("title"));
		video = videoService.getVideo(request.getParameter("title"));
		out.println("<html>"
				+ "<head>"
					+ "<title>Video Update Page</title>"
						+"<link rel='stylesheet' type='text/css' href='/zadaca3/css/admin.css'>"
						+"<link rel='stylesheet' type='text/css' "
							+ "href='https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.css'>"
				+ "</head>"
				+ "<body>"
					+ "<h1 align='center' >Add New Video</h1>"
						+ "<form action='/zadaca3/admin/edit' method='post'>"
						+ "<table style='width:50%' align='center'>"
						+ "<tr>"
							+ "<td>"
								+ "<div align='center' class='text'>Video ID</div>"
								+ "</td>"
								+ "<td align='center'>"
									+ "<div class='ui input' style='width:90%'>"
											+ "<input type='text' name='id' value='https://www.youtube.com/watch?v="+video.getVideoId() +"'>"
										+ "</div>"
									+ "</td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td>"
									+ "<div  align='center' class='text'>Title</div>"
									+ "</td>"
									+ "<td align='center'>"
										+ "<div class='ui input' style='width:90%'>"
											+ "<input type='text' name='name' value='" + video.getVideoName()+"'>"
										+ "</div>"
									+ "</td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td>"
									+ "<div align='center' class='text'>Positive Votes</div>"
								+ "</td>"
								+ "<td align='center'>"
									+ "<div class='ui input' style='width:90%'>"
											+ "<input type='number' name='positive_votes' value='"+(Number)video.getPositiveVotes()+"'>"
										+ "</div>"
									+ "</td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td>"
									+ "<div align='center' class='text'>Total Votes</div>"
								+ "</td>"
								+ "<td align='center'>"
									+ "<div class='ui input' style='width:90%'>"
											+ "<input type='number' name='total_votes' value='"+ (Number)video.getTotalVotes()+"'>"
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
								+ "<a href='/zadaca3/admin/main'>" 
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
	
	if(request.getParameter("positive_votes").isEmpty() || request.getParameter("total_votes").isEmpty()) {
		JOptionPane.showMessageDialog(null, "Fields can not be empty or less than zero");
		response.sendRedirect("/zadaca3/admin/edit?title="+video.getVideoName());
	}
	else {
	Integer pos = Integer.parseInt(request.getParameter("positive_votes"));
	Integer total = Integer.parseInt(request.getParameter("total_votes"));
	String url_string =request.getParameter("id");
	String name=request.getParameter("name");
	if(url_string.isEmpty() || name.isEmpty())
	{
		JOptionPane.showMessageDialog(null, "Fields can not be empty or less than zero");
		response.sendRedirect("/zadaca3/admin/edit?title="+video.getVideoName());
	}
	else 
	{
		if(total < pos || pos < 0 || total < 0 )
		{
			JOptionPane.showMessageDialog(null, "Total votes need to be greater than positive votes and bouth greater than zero");
			response.sendRedirect("/zadaca3/admin/edit?title="+video.getVideoName());
		}
		else { 
			String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
			Pattern compiledPattern = Pattern.compile(pattern);
			Matcher matcher = compiledPattern.matcher(url_string); //url is youtube url for which you want to extract the id.
			if (matcher.find()) {
					Video temp = new Video();
					temp.setVideoId(matcher.group());
					temp.setVideoName(name);
					temp.setPositiveVotes(pos);
					temp.setTotalVotes(total);
					temp.setRank(pos,total);
					videoService.fullupdate(video, temp);	
					response.sendRedirect("/zadaca3/admin/main");
					}
					else {
						JOptionPane.showMessageDialog(null, "Link does not contain a valid id for this app");
						response.sendRedirect("/zadaca3/admin/newVideo");
					}	
				}
			}
		}
	}
}
