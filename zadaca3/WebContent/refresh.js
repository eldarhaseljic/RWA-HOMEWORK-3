 function refresh(){
		$.ajax({
			url:"/zadaca3/VoteServlet",
			type:"POST",
			dataType:"json",
			data:{"data":"refresh"},
			success: function(data){
				$("#title1").html(data[0].videoName);
				$("#title2").html(data[1].videoName);
	            $("#videoID1").attr('src','https://www.youtube.com/embed/' + data[0].videoId);
	            $("#videoID2").attr('src','https://www.youtube.com/embed/' + data[1].videoId);
			}
		});
}