package com.niit.RestController;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.UserInfoDAO;
import com.niit.model.UserInfo;

@RestController
public class UserInfoRestController {
	
	@Autowired
	UserInfoDAO userInfoDAO;
	
	@PostMapping("/userRegistration")
	public ResponseEntity<String> userRegisteration(@RequestBody UserInfo userInfo)
	{
		
		if(userInfoDAO.registerUser(userInfo))
		{
			
			return new ResponseEntity<String>("Registration Successfull", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error Occoured In Registration", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/loggingInProcess")
	public ResponseEntity<UserInfo> LoginIn(@RequestBody UserInfo userInfo , HttpSession httpSession)
	{
		UserInfo userInfo2 = userInfoDAO.getUser(userInfo.getLoginname());
		
		if(userInfo2 !=null)
		{
			httpSession.setAttribute("User Information", userInfo2);
			return new ResponseEntity<UserInfo>(userInfo2, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<UserInfo>(userInfo2, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/updateUserInfo")
	public ResponseEntity<String> updateUserInfo(@RequestBody UserInfo userInfo)
	{
		
		if(userInfoDAO.registerUser(userInfo))
		{
			
			return new ResponseEntity<String>("update Successfull", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Error Occoured In updateUser", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
