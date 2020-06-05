<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="domain.Video, service.VideoService, java.util.*, dao.VideoDao"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

	<!-- Add icon library -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

	<link rel="icon" href="images/clipboard.png" type="image/icon type">
	<link rel="stylesheet" href="css/stylesheet.css" type="text/css">
	<title>eldar_haseljic_zadaca1</title>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js" type="text/javascript"></script>
</head>

<body>

	<%
		VideoService service = new VideoService(new VideoDao());
		List<Video> videos = service.getRand();
	%>

	<div class="Container">
		<div class="bar">
			<a href="index.jsp"><span class="bar bar-span">Video voting competition</span></a>
		</div>
		<div class="menu">
			<a href="login.jsp"><button class="btn">Log in</button></a>
			<a href="#"><button class="btn"><i class="fa fa-share"></i></button></a>
			<button class="btn" id="refreshVideo" onclick="javaScript:refresh()"><i class="fa fa-refresh"></i></button>
			<a href="rankings.jsp"><button class="btn"><i class="fa fa-bars"></i> Rankings </button></a>
		</div>
		<div>
			<div>
				<h1 id="title1" class="title"><%=videos.get(0).getVideoName()%></h1>
				<h1 id="title2" class="title"><%=videos.get(1).getVideoName()%></h1>
			</div>
			<div>
				<iframe id="videoID1" src='<%="https://www.youtube.com/embed/" + videos.get(0).getVideoId()%>'></iframe>
				<iframe id="videoID2" src='<%="https://www.youtube.com/embed/" + videos.get(1).getVideoId()%>'></iframe>
			</div>
			<div>
				<button class="btn vote-btn" onclick="javaScript:vote('<%=videos.get(0).getVideoName()%>')"
					type="button">
					<i class="fa fa-check"></i>
				</button>
				<button class="btn vote-btn" onclick="javaScript:vote('<%=videos.get(1).getVideoName()%>')"
					type="button">
					<i class="fa fa-check"></i>
				</button>
			</div>
		</div>
		<div>
			<div class="small-ranking">
				<h1 class="title rank-title">
					<i class="fa fa-th-list"></i> Top 5 videos
				</h1>
			</div>

			<%
				Integer num = 5;
				ArrayList<Video> top5 = service.getVideos(num);
				for (Integer i =0; i<top5.size();++i) {
			%>
			<div>
				<!--First element on the rank list-->
				<div class="row">
					<div class="column">
						<img src='<%="https://img.youtube.com/vi/" + top5.get(i).getVideoId() + "/maxresdefault.jpg"%>'
							class="thumbnail"/>
					</div>
					<div class="column">
						<h1 class="rank-head">Title</h1>
						<p class="column-text" style="font-size: 2vmax; margin-top: 5%"><%=top5.get(i).getVideoName()%>
						</p>
					</div>

					<div class="column column-votes">
						<h1 class="rank-head" style="font-size: 5vmax; margin-top: 0%">Rank</h1>
						<p class="column-text" style="font-size: 3.5vmax; padding: 0%; margin: 2%"><%="#"+(i+1)%></p>
						<h1 class="rank-head" style="font-size: 1.5vmax; margin-top: 0%">Positive / Total Votes</h1>
						<p class="column-text"><%=top5.get(i).getPositiveVotes() + " / " + top5.get(i).getTotalVotes()%>
						</p>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
		<div class="footer">
			<p>Designed by: Eldar Haseljić 2020.05.12</p>
		</div>
	</div>
	<script type="text/javascript" src="vote.js"></script>
	<script type="text/javascript" src="refresh.js"></script>
</body>

</html>