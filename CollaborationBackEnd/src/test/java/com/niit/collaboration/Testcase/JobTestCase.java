package com.niit.collaboration.Testcase;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.JobDAO;

import com.niit.collaboration.model.Job;

public class JobTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Job job;

	@Autowired
	private static JobDAO jobDAO;

	@BeforeClass
	public static void inti() {

		context = new AnnotationConfigApplicationContext();

		context.scan("com.niit.collaboration");

		context.refresh();

		job = (Job) context.getBean("job");

		jobDAO = (JobDAO) context.getBean("jobDAO");

	}

	@Test
	public void CreateTestCase() {
		job.setId("job01");
		job.setTitle("this is job for TPO");
		boolean flag = jobDAO.saveOrupdate(job);

		assertEquals("CreateTestCase", true, flag);
	}

	// @Test
	public void deleteByIdTestCase() {

		boolean flag = jobDAO.deleteById("1");

		assertEquals("deleteByIdTestCase", true, flag);

	}
	
	/*//@Test
	public void deleteByNameTestCase() {

		boolean flag = jobDAO.deleteById("niit");

		assertEquals("deleteByIdTestCase", true, flag);

	}*/

	 //@Test
	public void deletebyJobTestCase() {

		
		boolean flag = jobDAO.deleteById("job01");

		assertEquals("deleteByIdTestCase", true,flag);

	}
	
	//@Test
	public void ListTestCase(){
		
		int size = jobDAO.list().size();
		
		assertEquals("ListTestCase",1,size);
	}
	
	//@Test
	/*public void getJobByNameTestCase()
	{
		
		job =  jobDAO.getJobByName("sdsds");
		
		assertEquals(" getJobByNameTestCase",null,job);
		
	}
	
*/
}
