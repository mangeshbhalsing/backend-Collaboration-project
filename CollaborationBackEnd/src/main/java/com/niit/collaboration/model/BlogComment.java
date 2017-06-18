package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="BLOG_COMMENT")

public class BlogComment extends BaseDomain {
	
	@Id
	private Long commentId ;
		
	private int blogId ;
	
	private String description ;
	
	private String userId ;
	
	private String commentDate ;
	
	private String likes ;
	
	private String dislikes ;
	
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long long1) {
		this.commentId = long1;
	}
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String date) {
		this.commentDate = date;
	}
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getDislikes() {
		return dislikes;
	}
	public void setDislikes(String dislikes) {
		this.dislikes = dislikes;
	}

	
	
	
	
}
