package test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.UserDao;
import dao.VideoDao;
import domain.User;
import domain.Video;
import service.UserService;
import service.VideoService;

public class testBaza {
	public static void main(String[] args) {
		String PERSISTENCE_UNIT_NAME = "zadaca3";
		EntityManagerFactory emf;
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = emf.createEntityManager();
		Query q2 = em.createQuery("SELECT s FROM Video s");
		@SuppressWarnings("unchecked")
		List<Video> videos = q2.getResultList();

		for (Video o : videos)
			System.out.println(o);
		System.out.println();

		VideoService s = new VideoService(new VideoDao());
		ArrayList<Video> t = s.getVideos(5);

		for (Video o : t)
			System.out.println(o);
		System.out.println();

		UserService userService = new UserService(new UserDao());
		List<User> t1 = userService.getAllUsers();

		for (User o : t1)
			System.out.println(o);
		System.out.println();

		System.out.println(userService.getUser("eldar.haseljic", "12345"));
		System.out.println(t1.get(2).getRoles());
		System.out.println(t1.get(0).isAdmin(t1.get(0).getRoles()));
		
		System.out.println(userService.getAllUsers());
		userService.updateUser(userService.getUser("eldar.haseljic", "12345"), new User("eldar.haseljic","12345","admin"));
		System.out.println(userService.getAllUsers());
	}
}