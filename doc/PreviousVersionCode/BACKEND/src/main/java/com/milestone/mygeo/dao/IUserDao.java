package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.User;



public interface IUserDao {
	
	public List<User> getAllUser();	
	
	public	void insertUser(User user);
	
	public	void updateUser(User user);
	
	public void deleteUserById(int id);
	
	public	User getUserByName(String name);
}
