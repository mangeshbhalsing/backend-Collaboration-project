package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Chat;



public interface ChatDAO {

	public Chat getChatById(String id);
	
	public Chat getChatByName(String name);
	
	public boolean saveOrupdate(Chat chat);
	
	public boolean deleteById(String id);
	
	/*public boolean deletebyChat(Chat chat);
	
	public boolean deleteByName(String name);
	*/
	public List<Chat> list();
	
	
}
