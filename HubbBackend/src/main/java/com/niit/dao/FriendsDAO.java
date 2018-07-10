package com.niit.dao;

import java.util.List;

import com.niit.model.Friends;
import com.niit.model.UserInfo;

public interface FriendsDAO {
	
	public List<Friends> showFriendList(String loginname);
	
	public List<Friends> showPendingFriendRequest(String loginname);
	
	public List<UserInfo> showSuggestedFriend(String loginname);
	
	public boolean acceptFriendRequest(int friendId);
	
	public boolean sendFriendRequest(Friends friends);
	
	public boolean deleteFriendRequest(int friendId);

}
