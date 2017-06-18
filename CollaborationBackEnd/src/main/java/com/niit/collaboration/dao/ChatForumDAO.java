package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.ChatForum;

public interface ChatForumDAO {

	public boolean saveOrupdate(ChatForum chatForum);

	// public boolean update(ChatForum ChatForum);

	public boolean deleteById(String id);

	public ChatForum getChatForumById(String id);

	public ChatForum getChatForumByName(String name);

	public List<ChatForum> list();

	
}
