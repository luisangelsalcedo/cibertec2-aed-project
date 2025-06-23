package eparking.interfaces;

import java.util.List;
import eparking.models.User;

public interface IUserDAO {
	List<User> getAllUsers();
	User findUserByUserName(String userName);
	void insertUser(User user);
	void updateUser(User user);
}