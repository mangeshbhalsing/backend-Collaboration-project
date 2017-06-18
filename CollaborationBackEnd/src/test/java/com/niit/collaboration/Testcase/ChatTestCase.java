package com.niit.collaboration.Testcase;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.BlogDAO;

import com.niit.collaboration.model.Blog;

public class ChatTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Blog blog;

	@Autowired
	private static BlogDAO blogDAO;

	@BeforeClass
	public static void inti() {

		context = new AnnotationConfigApplicationContext();

		context.scan("com.niit.collaboration");

		context.refresh();

		blog = (Blog) context.getBean("blog");

		blogDAO = (BlogDAO) context.getBean("blogDAO");

	}

	// @Test
	public void CreateTestCase() {
		blog.setId(01);
		blog.setTitle("This is the blog android ");
		blog.setDescription("This is the desciption of bolg");
		blog.setUserID("niit");
	//	blog.setStatus('A');// accept reject New
		blog.setReason("kjdkjhfhdfdhfhdfh");

		boolean flag = blogDAO.save(blog);

		assertEquals("CreateTestCase", true, flag);
	}

	// @Test
	public void deleteByIdTestCase() {

		boolean flag = blogDAO.deleteById("1");

		assertEquals("deleteByIdTestCase", true, flag);

	}
	
	/*//@Test
	public void deleteByNameTestCase() {

		boolean flag = blogDAO.deleteById("niit");

		assertEquals("deleteByIdTestCase", true, flag);

	}*/

	 //@Test
	public void deletebyBlogTestCase() {

		blog.setId(82);
//		boolean flag = blogDAO.deletebyBlog(blog);

//		assertEquals("deleteByIdTestCase", true,flag);

	}
	
	//@Test
	public void ListTestCase(){
		
		int size = blogDAO.list().size();
		
		assertEquals("ListTestCase",1,size);
	}
	
	@Test
	public void getBlogByNameTestCase()
	{
		
		blog =  blogDAO.getBlogByName("sdsds");
		
		assertEquals(" getBlogByNameTestCase",null,blog);
		
	}
	

}
