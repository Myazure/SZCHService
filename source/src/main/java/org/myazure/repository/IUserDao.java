package org.myazure.repository;

import java.util.List;

import org.myazure.domain.User;



public interface IUserDao {
	
	public List<User> getAllUser();	
	
	public	void insertUser(User user);
	
	public	void updateUser(User user);
	
	public void deleteUserById(int id);
	
	public	User getUserByName(String name);
}
