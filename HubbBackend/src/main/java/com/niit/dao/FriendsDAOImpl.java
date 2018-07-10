package com.niit.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Friends;
import com.niit.model.UserInfo;

@Repository("friendsDAO")
@Transactional
public class FriendsDAOImpl implements FriendsDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Friends> showFriendList(String loginname) {

		Session session = sessionFactory.openSession();
		Query query = session.createQuery(
				"from Friends where (loginname=:userloginname or friendloginname=:friendlogin) and status='A'");
		query.setParameter("userloginname", loginname);
		query.setParameter("friendlogin", loginname);
		List<Friends> ListOfFriends = (List<Friends>) query.list();
		return ListOfFriends;
	}

	@Override
	public List<Friends> showPendingFriendRequest(String loginname) {

		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Friends where friendloginname=:myloginname and status='P'");
		query.setParameter("myloginname", loginname);
		List<Friends> listFriends = (List<Friends>) query.list();
		return listFriends;
	}

	@Override
	public boolean acceptFriendRequest(int friendId) {

		try {
			Session session = sessionFactory.openSession();
			Friends friends = session.get(Friends.class, friendId);
			System.out.println("Login Name" + friends.getLoginname());
			friends.setStatus("A");
			session.update(friends);
			System.out.println("Updated");
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean sendFriendRequest(Friends friends) {

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friends);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteFriendRequest(int friendId) {

		try {
			Session session = sessionFactory.openSession();
			Friends friends = session.get(Friends.class, friendId);
			session.delete(friends);
			session.flush();
			session.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<UserInfo> showSuggestedFriend(String loginname) {

		String str = "select loginname from A_UserInfo where (loginname not in(select friendloginname from A_Friend where loginname='"+loginname+"' and status = 'A') and loginname not in(select loginname from A_Friend where friendloginname='"+loginname+"' and status = 'A'))and loginname!='"+loginname+"'";

		Session session = sessionFactory.openSession();
		Query query = session.createSQLQuery(str);
		List<String> username = (List<String>)query.list();
		ArrayList<UserInfo> suggestLisytOfFriends = new ArrayList<UserInfo>();
		int j=0;
		while(j<username.size())
		{
			UserInfo userInfo = (UserInfo)session.get(UserInfo.class, username.get(j));
			suggestLisytOfFriends.add(userInfo);
			j++;
		}

		return suggestLisytOfFriends;
	}

}
