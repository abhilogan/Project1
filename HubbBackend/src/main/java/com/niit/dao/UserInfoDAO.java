package com.niit.dao;

import com.niit.model.UserInfo;

public interface UserInfoDAO {
	
	public boolean registerUser(UserInfo userInfo);
	
	public boolean updateUser(UserInfo userInfo);
	
	public UserInfo getUser(String loginname);
	
	public UserInfo checkUser(UserInfo userInfo);

}
