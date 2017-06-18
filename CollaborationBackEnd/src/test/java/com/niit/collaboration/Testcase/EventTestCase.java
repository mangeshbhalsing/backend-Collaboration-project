package com.niit.collaboration.Testcase;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Event;


public class EventTestCase {

	@Autowired
	private static AnnotationConfigApplicationContext context;

	@Autowired
	private static Event event;

	@Autowired
	private static EventDAO eventDAO;

	@BeforeClass
	public static void inti() {

		context = new AnnotationConfigApplicationContext();

		context.scan("com.niit.collaboration");

		context.refresh();

		event = (Event) context.getBean("event");

		eventDAO = (EventDAO) context.getBean("eventDAO");

	}

	 @Test
	public void CreateTestCase() {
		
		 
		
		event.setName("Android");
		event.setVenue("Andhri");
		event.setDescription("This the event that ");
		event.setEventDate("10 june");
		
		boolean flag = eventDAO.save(event);

		assertEquals("CreateTestCase", true, flag);
	}

	// @Test
	public void deleteByIdTestCase() {

		boolean flag = eventDAO.deleteById("1");

		assertEquals("deleteByIdTestCase", true, flag);

	}
	
	/*//@Test
	public void deleteByNameTestCase() {

		boolean flag = eventDAO.deleteById("niit");

		assertEquals("deleteByIdTestCase", true, flag);

	}*/

	/* //@Test
	public void deletebyEventTestCase() {

		event.setId(id)
		boolean flag = eventDAO.deletebyEvent(event);

		assertEquals("deleteByIdTestCase", true,flag);

	}
	*/
	//@Test
	public void ListTestCase(){
		
		int size = eventDAO.list().size();
		
		assertEquals("ListTestCase",1,size);
	}
	
	//@Test
	public void getEventByNameTestCase()
	{
		
		event =  eventDAO.getEventByName("sdsds");
		
		assertEquals(" getEventByNameTestCase",null,event);
		
	}
	

}
