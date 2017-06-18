package com.niit.collaboration.Testcase;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.collaboration.dao.BlogCommentDAO;
import com.niit.collaboration.model.BlogComment;

public class BlogCommentTestCase {
	
	@Autowired
	private static AnnotationConfigApplicationContext context;
	
	@Autowired
	private static BlogComment blogComment;
	
	@Autowired
	private static BlogCommentDAO blogCommentDAO;

	
	@BeforeClass
	public static void inti() {

		context = new AnnotationConfigApplicationContext();

		context.scan("com.niit.collaboration");

		context.refresh();

		blogComment = (BlogComment) context.getBean("blogComment");

		blogCommentDAO = (BlogCommentDAO) context.getBean("blogCommentDAO");

	}
	
	
	//@Test
	public void  CreateTestcase(){
		
		//blogComment.setCommentId(100l);
		blogComment.setDescription("i dont like your blog that much that is liked hitesh blog");
		blogComment.setBlogId(101);
		blogComment.setUserId("hitu");
	//	blogComment.setCommentDate(new Date());
//		blogComment.setLikes(5);
//		blogComment.setDislikes(10);
		
		boolean flag =  blogCommentDAO.saveComment(blogComment);

		assertEquals("CreateTestCase", true, flag);
		
	}
	
	/*@Test
	public void  UpdateTestcase(){
		
		//blogComment.setCommentId(100l);
		blogComment.setDescription("i dont like your blog that much that is liked hitesh blog");
		blogComment.setBlogId(101);
		blogComment.setUserId("hitu");
		blogComment.setCommentDate(new Date());
		blogComment.setLikes(5);
		blogComment.setDislikes(10);
		
		boolean flag =  blogCommentDAO.saveComment(blogComment);

		assertEquals("CreateTestCase", true, flag);
		
	}
	*/
	
	
	//@Test
	public void getCommentByIdTestCase(){
		
		blogComment = blogCommentDAO.getCommentById(105);
		
		assertEquals("getCommentByIdTestCase ",null,blogComment);
		
		
		
		
	}
	
	
	/*//@Test
	public void getCommentUserIdTestCase(){
		
	//	blogComment = blogCommentDAO.getComment("hitudsd");
		
		assertEquals("getCommentUserIdTestCase",null,blogComment);
		
		
		
		
	}
	*/
	@Test
	public void getBlogIdTestCase(){
		
		int value = blogCommentDAO.getComments(101).size();
		
		assertEquals("getBlogIdTestCase",3,value);
		
		
		
		
	}
}
