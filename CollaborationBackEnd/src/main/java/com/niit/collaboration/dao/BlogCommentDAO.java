package com.niit.collaboration.dao;

import java.util.List;

import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.BlogComment;

public interface BlogCommentDAO {
	
	public List<BlogComment> list();
	
	public boolean saveComment(BlogComment blogComment);
	public boolean updateComment(BlogComment blogComment);
	public boolean deleteComment(int blogCommentId);
	public List<BlogComment> getComment(String userId);
	public List<BlogComment> getComments(int blogId);
	
	public BlogComment getCommentById(int blogCommentId);
	
	


}
