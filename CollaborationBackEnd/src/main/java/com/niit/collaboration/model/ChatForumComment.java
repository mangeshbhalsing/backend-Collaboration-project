package com.niit.collaboration.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="C_FORUM_COMMENT")
public class ChatForumComment extends  BaseDomain{
	
	@Id
	private String id;
	
	@Column(name="forum_id")
	private String forumID;
	
	@Column(name="user_id")
	private String userID;
	
	private String message;
	
	@Column(name="commented_date")
	private Date DateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getForumID() {
		return forumID;
	}

	public void setForumID(String forumID) {
		this.forumID = forumID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateTime() {
		return DateTime;
	}

	public void setDateTime(Date dateTime) {
		DateTime = dateTime;
	}
	
	
	
	

}
