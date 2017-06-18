package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.ChatForumComment;

public interface ChatForumCommentDAO {

public boolean saveOrupdate(ChatForumComment chatForumComment);
	
	//public boolean update(ChatForumComment ChatForumComment);
	
	public List<ChatForumComment> list();
	
	public ChatForumComment get(String id);
	
}
