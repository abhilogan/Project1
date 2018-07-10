package com.niit.testCases;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.UserInfoDAO;
import com.niit.model.UserInfo;

public class UserDAOTestCase {
	
	@Autowired
	static UserInfoDAO userInfoDAO;

	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		userInfoDAO=(UserInfoDAO)context.getBean("userInfoDAO");
	}

	@Test
	public void registerUserTestCase()
	{
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginname("bhupesh");
		userInfo.setPassword("bhupesh@123");
		userInfo.setUsername("bhupesh sharma");
		userInfo.setRole("R_User");
		userInfo.setEmailId("hachiko@gmail.com");
		userInfo.setAddress("Ratlam");
		userInfo.setMobile("5656565656");
		
		assertTrue("Problem in registering new User", userInfoDAO.registerUser(userInfo));
	}
	
	@Test
	public void UpdateUserTestCase()
	{
		UserInfo userInfo = userInfoDAO.getUser("abhishek");
		userInfo.setEmailId("abhishek@gmail.com");
		
		assertTrue("Problem in Updating user", userInfoDAO.updateUser(userInfo));
	}
	
	@Test
	public void checkLoginTestCase()
	{
		UserInfo userInfo = new UserInfo();
		userInfo.setLoginname("abhishek");
		userInfo.setPassword("abhi@123");
		
		UserInfo userInfo2 =userInfoDAO.checkUser(userInfo);
		
		assertNotNull("Problem in loginCheck",userInfo2);
		
		System.out.println("EmailID Name=:"+userInfo2.getUsername()+" :::: UserName=:"+userInfo2.getUsername());
	}
	
}


