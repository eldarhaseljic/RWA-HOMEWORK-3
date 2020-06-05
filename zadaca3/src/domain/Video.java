package domain;

import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
public class Video {

	@Column(name = "video_id")
	private String videoId;

	@Id
	@Column(name = "video_name")
	private String videoName;

	@Column(name = "positive_votes")
	private Integer positiveVotes;
	@Column(name = "total_votes")
	private Integer totalVotes;
	@Column(name = "rank")
	private Double rank;

	public Double getRank() {
		return Double.valueOf((new DecimalFormat("#.####")).format(rank));
	}

	public void setRank(Integer pos, Integer n) {
		Double z = 1.96;
		Double phat = 1.0 * pos / n;
		this.rank = (phat + z * z / (2 * n) - z * Math.sqrt((phat * (1 - phat) + z * z / (4 * n)) / n))
				/ (1 + z * z / n);
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public Integer getPositiveVotes() {
		return positiveVotes;
	}

	public void setPositiveVotes(Integer positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	public String getImage() {
		return "https://img.youtube.com/vi/" + this.videoId + "/maxresdefault.jpg";
	}
	@Override
	public String toString() {
		return "video_id=" + videoId + ", video_name=" + videoName + ", positive_votes=" + positiveVotes
				+ ", total_votes=" + totalVotes + ", rank=" + rank;
	}
}