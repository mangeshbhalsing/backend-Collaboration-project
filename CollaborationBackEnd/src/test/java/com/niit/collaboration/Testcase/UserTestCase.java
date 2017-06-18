package com.niit.collaboration.Testcase;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import com.niit.collaboration.config.ApplicationContextConfig;
import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

public class UserTestCase {
	

@Autowired
private static AnnotationConfigApplicationContext context;

@Autowired
private static User user;

@Autowired
private static UserDAO userDAO;


@BeforeClass
public static void inti(){
	
	context=new AnnotationConfigApplicationContext();
	
	context.scan("com.niit.collaboration");
	
	context.refresh();
	
	user= (User) context.getBean("user");
	
	userDAO =  (UserDAO) context.getBean("userDAO");
	
	
}

@Test
public void CreateTestCase(){
	
	user.setId("nilksfls");
	user.setName("niitjjj");
	user.setAddress("Bolinjjj");
	user.setPassword("niitj");
	user.setRole("Employee");
	user.setMobile("949494994");
	
	boolean flag = userDAO.saveOrupdate(user);
	
	
	
	assertEquals("CreateTestCase",true,flag);
}

//@Test
public void UpdateTestCase(){
	user.setId("niit");
	user.setName("niit");
	user.setAddress("Bolinj naka");
	user.setPassword("niit");
	user.setRole("Admin");
	user.setMobile("949494994");
	
	boolean flag = userDAO.saveOrupdate(user);
	
	
	
	assertEquals("UpdateTestCase",true,flag);
	
	
}

//@Test
/*public void ValidateTestcase()
{

	
	boolean flag = userDAO.isValidate("niit", "niit");
	
	assertEquals("ValidateTestcase",true,flag);
	
	
}*/

//@Test
public void ListTestCase(){
	
	int size = userDAO.list().size();
	
	assertEquals("ListTestCase",1,size);
}

//@Test
public void getUserTestcase(){
	
	user = userDAO.get("nii");
	
	assertEquals("getUserTestcase",null,user);
	
}

/*//@Test
public void IsOnlineTestCase(){
	
	boolean flag=userDAO.setOnline("niit");
	
	assertEquals("IsOnlineTestCase",true,flag);
}
*/
/*@Test
public void IsOfflineTestCase(){
	
	boolean flag=userDAO.setoffline("hitu");
	
	assertEquals("IsOfflineTestCase",true,flag);
}
*/


	
}
