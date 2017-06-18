package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.User;

public interface UserDAO {

	public boolean saveOrupdate(User user);

	// public boolean update(User user);

	public List<User> notMyFriendList(String userID);

	public List<User> list();

	public User get(String id);

	public User isValidate(String id, String password);

	public void setOnline(String id);

	public void setoffline(String id);

}
