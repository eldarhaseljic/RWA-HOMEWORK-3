package service;

import java.util.List;

import dao.UserDao;
import domain.User;

public class UserService {

	private UserDao userDao;

	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}

	public void create(User user) {
		userDao.saveUser(user);
	}

	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	public User getUser(String userName, String password) {
		// TODO Auto-generated method stub
		return userDao.getUser(userName, password);
	}

	public User getUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.getUserByUserName(userName);
	}

	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}

	public void updateUser(User user, User updateUser) {
		// TODO Auto-generated method stub
		userDao.updateUser(user,updateUser);
	}

	public void deleteUser(String userName) {
		// TODO Auto-generated method stub
		userDao.deleteUser(userName);
	}

}
