<%@page import="java.util.List"%>
<%@page import="domain.Video"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.VideoDao"%>
<%@page import="service.VideoService"%>
<%@ page language="java"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

<!-- Add icon library -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
<link rel="icon" href="images/top10.png" type="image/icon type">
<title>Rankings</title>
</head>

<body>

	<div class="bar">
		<a href="index.jsp"> <span class="bar bar-span">Video
				voting competition</span></a>
	</div>
	<%
		String spageid = request.getParameter("page");
	int pageid;
	if (spageid == null) {
		pageid = 1;
	} else {
		pageid = Integer.parseInt(spageid);
	}
	%>
	<div class="menu">
		<a href="login.jsp"><button class="btn">Log in</button></a> <a
			href="#">
			<button class="btn">
				<i class="fa fa-share"></i>
			</button>
		</a> <a href="<%="rankings.jsp?page=" + pageid%>">
			<button class="btn">
				<i class="fa fa-refresh"></i>
			</button>
		</a> <a href="index.jsp">
			<button class="btn">
				<i class="fa fa-home"></i>
			</button>
		</a>
	</div>

	<div>
		<div class="full-ranking">
			<h1 class="title rank-title-max">
				<i class="fa fa-line-chart "></i> Rankings
			</h1>
		</div>
		<div>
			<%
				int num = 20;
			VideoService service = new VideoService(new VideoDao());
			List<Video> top = service.findAll();
			int pagerBegin = 1;
			int pagerEnd = top.size() / num;
			for (int i = num * (pageid - 1); i < num + num * (pageid - 1); ++i) {
			%>
			<div>
				<!--First element on the rank list-->
				<div class="row">
					<div class="column">
						<img
							src='<%="https://img.youtube.com/vi/" + top.get(i).getVideoId() + "/maxresdefault.jpg"%>'
							class="thumbnail" alt="" />
					</div>
					<div class="column">
						<h1 class="rank-head">Title</h1>
						<p class="column-text" style="font-size: 2vmax; margin-top: 5%"><%=top.get(i).getVideoName()%>
						</p>
					</div>

					<div class="column column-votes">
						<h1 class="rank-head" style="font-size: 5vmax; margin-top: 0%">Rank</h1>
						<p class="column-text"
							style="font-size: 3.5vmax; padding: 0%; margin: 2%"><%="#" + (i + 1)%>
						</p>
						<h1 class="rank-head" style="font-size: 1.5vmax; margin-top: 0%">Positive
							/ Total Votes</h1>
						<p class="column-text"><%=top.get(i).getPositiveVotes() + " / " + top.get(i).getTotalVotes()%>
						</p>
					</div>
				</div>
				<%
					}
				%>


				<div class="prev-next-field">
					<%
						for (Integer i = pagerBegin; i <= pagerEnd; ++i) {
						if (i == pagerBegin) {
					%>
					<a href="<%="rankings.jsp?page=" + i%>"><button
							class="next button-cicrle">First page</button></a>
					<%
						} else if (i == pagerEnd) {
					%>
					<a href="<%="rankings.jsp?page=" + i%>"><button
							class="next button-cicrle">Last page</button></a>
					<%} else {%>
					<a href="<%="rankings.jsp?page=" + i%>"><button
							class="next button-cicrle"><%=i%></button></a>
					<%
						}
					}
					%>
				</div>
				<div class="footer">
					<p>Designed by: Eldar Haseljić 2020.05.12</p>
				</div>
			</div>
		</div>
	</div>
</body>

</html>