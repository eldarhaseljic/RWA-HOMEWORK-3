package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.VideoDao;
import domain.Video;
import service.VideoService;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	RequestDispatcher dispatcher = null;
	VideoService service = new VideoService(new VideoDao());

	public VoteServlet() {
		// super();
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

		ArrayList<Video> videos = service.getRand();
		request.setAttribute("title1", videos.get(0).getVideoName());
		request.setAttribute("title2", videos.get(1).getVideoName());
		request.setAttribute("id1", "https://www.youtube.com/embed/" + videos.get(0).getVideoId());
		request.setAttribute("id2", "https://www.youtube.com/embed/" + videos.get(1).getVideoId());

		dispatcher = request.getRequestDispatcher("/index.jsp");
		// System.out.println("hello");
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
		if (request.getParameter("data").equals("vote")) {
			String title1 = request.getParameter("title1");
			String title2 = request.getParameter("title2");
			Video video1 = service.getVideo(title1);
			Video video2 = service.getVideo(title2);

			if (request.getParameter("voted").equals(video1.getVideoName())) {
				video1.setPositiveVotes(video1.getPositiveVotes() + 1);
				video1.setTotalVotes(video1.getTotalVotes() + 1);
				video2.setTotalVotes(video2.getTotalVotes() + 1);
			} else {
				video2.setPositiveVotes(video2.getPositiveVotes() + 1);
				video2.setTotalVotes(video2.getTotalVotes() + 1);
				video1.setTotalVotes(video1.getTotalVotes() + 1);
			}

			// System.out.println(service.getVideo(video1.getVideoName()));
			service.update(video1);
			// System.out.println(service.getVideo(video1.getVideoName()));
			// System.out.println(service.getVideo(video2.getVideoName()));
			service.update(video2);
			// System.out.println(service.getVideo(video2.getVideoName()));
		}

		ArrayList<Video> newVideosToVote = service.getRand();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(newVideosToVote));
	}

}
