 function vote(video){
	   var video1 = document.getElementById("title1").innerText
	   var video2 = document.getElementById("title2").innerText
		$.ajax({
			url:"/zadaca3/VoteServlet",
			type:"POST",
			dataType:"json",
			data:{"data":"vote", "title1":video1, "title2":video2, "voted":video},
			success: function(data){
				$("#title1").html(data[0].videoName);
				$("#title2").html(data[1].videoName);
	            $("#videoID1").attr('src','https://www.youtube.com/embed/' + data[0].videoId);
	            $("#videoID2").attr('src','https://www.youtube.com/embed/' + data[1].videoId);
			}
		});
}