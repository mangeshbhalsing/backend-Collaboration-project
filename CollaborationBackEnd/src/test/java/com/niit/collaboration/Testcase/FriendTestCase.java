package com.niit.collaboration.Testcase;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.FriendDAO;
import com.niit.collaboration.model.Friend;


public class FriendTestCase {
	
	@Autowired
	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Friend friend;

	@Autowired
	private static FriendDAO friendDAO;


	@BeforeClass
	public static void inti(){
		
		context=new AnnotationConfigApplicationContext();
		
		context.scan("com.niit.collaboration");
		
		context.refresh();
		
		friend= (Friend) context.getBean("friend");
		
		friendDAO =  (FriendDAO) context.getBean("friendDAO");
		
		
	}
	
	 @Test
		public void CreateTestCase() {
			
		 friend.setUserId("mb");
		 friend.setFriendId("admin");
		 friend.setIsOnline('N');
	 friend.setStatus("N");
		 
			boolean flag = friendDAO.saveFriend(friend);

	//		assertEquals("CreateTestCase", true, flag);
		}

	// @Test
	 public void IsOnlineTestCase(){
	 	
	 	boolean flag=friendDAO.setOnline("hitu");
	 	
	 	assertEquals("IsOnlineTestCase",true,flag);
	 }
	 
//	 @Test
//	 public void IsOfflineTestCase(){
//	 	
//	 	boolean flag=friendDAO.setOffLine("hitu");
//	 	
//	 	assertEquals("IsOfflineTestCase",true,flag);
//	 }

}
