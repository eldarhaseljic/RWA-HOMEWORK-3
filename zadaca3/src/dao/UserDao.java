package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import domain.User;

final public class UserDao extends AbstractDao {
	public UserDao() {

	}

	public List<User> getAllUsers() {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		@SuppressWarnings("unchecked")
		List<User> resultList = q.getResultList();
		em.close();
		return resultList;
	}

	public User getUserByUserName(String userName) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u WHERE u.userName = :a", User.class);
		q.setParameter("a", userName);
		@SuppressWarnings("unchecked")
		List<User> results = q.getResultList();
		em.close();
		if (results.isEmpty())
			return null;
		return results.get(0);
	}

	public User getUser(String userName, String password) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u WHERE u.userName = :a and u.password = :b", User.class);
		q.setParameter("a", userName);
		q.setParameter("b", password);
		@SuppressWarnings("unchecked")
		List<User> results = q.getResultList();
		em.close();
		if (results.isEmpty())
			return null;
		return results.get(0);
	}

	public void saveUser(User user) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public void deleteUser(String userName) {
		EntityManager em = createEntityManager();
		Query q = em.createQuery("DELETE FROM User u WHERE u.userName = :userName", User.class);
		q.setParameter("userName", userName);
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

	public void updateUser(User user, User updateUser) {
		// TODO Auto-generated method stub
		EntityManager em = createEntityManager();
		Query q = em.createQuery("UPDATE User u SET u.userName = :userName, u.password = :password, u.roles = :roles WHERE u.userName = :user", User.class);
		q.setParameter("userName", updateUser.getUserName());
		q.setParameter("password", updateUser.getPassword());
		if(updateUser.getRoles().size()==1) {
			q.setParameter("roles", updateUser.getRoles().get(0));
		}
		else
		{
			q.setParameter("roles", updateUser.getRolesString());
		}
		q.setParameter("user", user.getUserName());
		em.getTransaction().begin();
		q.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

}