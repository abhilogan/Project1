package com.niit.testCases;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.FriendsDAO;
import com.niit.model.Friends;
import com.niit.model.UserInfo;

public class FriendsDAOTestCases {

	@Autowired
	static FriendsDAO friendsDAO;

	@BeforeClass
	public static void init()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		friendsDAO=(FriendsDAO) context.getBean("friendsDAO");
	}
	
	@Ignore
	@Test
	public void sendFriendTest()
	{
		Friends friends=new Friends();
		friends.setLoginname("abhishek");
		friends.setFriendloginname("amey");
		friends.setStatus("P");
		assertTrue("Sending friend Request Error",friendsDAO.sendFriendRequest(friends));
	}
	
	@Ignore
	@Test
	public void AcceptFriendRequestTestCases()
	{
		assertTrue("Error Occoured in Accepting the Friend Request", friendsDAO.acceptFriendRequest(21));
		
	}
	
	@Ignore
	@Test
	public void deleteFriendRequestTestCases()
	{
		assertTrue("Error Occoured in Deleting the Friend Request", friendsDAO.deleteFriendRequest(21));
	}
	
	@Test
	public void SuggestFriendsListTestCases()
	{
		List<UserInfo> listOfUSerInfo = (List<UserInfo>)friendsDAO.showSuggestedFriend("abhishek");
		assertTrue("Problem Occoured in Suggesting List Of Friends", listOfUSerInfo.size()>0);
		
		System.out.println("-------------Friends Suggest List--------------");
		for(UserInfo userInfo : listOfUSerInfo)
		{
			System.out.println(userInfo.getLoginname() + "***" +userInfo.getUsername());
		}
	}
	
	
	@Test
	public void showPendingRequest()
	{
		List<Friends> listFriend=friendsDAO.showPendingFriendRequest("amey");
		
		assertTrue("Problem in Listing Friend",listFriend.size()>0);
		
		for(Friends friends:listFriend)
		{
			System.out.println("Login Name:"+friends.getLoginname()+"Friend Name:"+friends.getFriendloginname());
		}
		
	}
	

}
