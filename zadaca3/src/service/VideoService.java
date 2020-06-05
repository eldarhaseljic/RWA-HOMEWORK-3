package service;

import java.util.ArrayList;
import java.util.List;

import dao.VideoDao;
import domain.Video;

public class VideoService {

	private VideoDao videoDao;

	public VideoService(VideoDao videoDao) {
		this.videoDao = videoDao;
	}

	public void create(Video video) {
		videoDao.save(video);
	}

	public List<Video> findAll() {
		return videoDao.findAll();
	}

	public ArrayList<Video> getRand() {
		return videoDao.getRandomVideos();
	}

	public ArrayList<Video> getVideos(Integer num) {
		return videoDao.getVideos(num);
	}

	public Video getVideo(String title) {
		// TODO Auto-generated method stub
		return videoDao.getVideo(title);
	}

	public void update(Video video) {
		// TODO Auto-generated method stub
		videoDao.updateVideoDetail(video);
	}

	public void fullupdate(Video oldVideo, Video newVideo) {
		// TODO Auto-generated method stub
		videoDao.fullupdate(oldVideo,newVideo);
	}

	public void delete(String videoTitle) {
		// TODO Auto-generated method stub
		videoDao.delete(videoTitle);
	}

}
