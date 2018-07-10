package com.niit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.UserInfo;

@Repository("userInfoDAO")
@Transactional
public class UserInfoDAOImpl implements UserInfoDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean registerUser(UserInfo userInfo) {
		try {
			sessionFactory.getCurrentSession().save(userInfo);
			return true;
		} catch (Exception e) {
			System.out.println("Exception Occoured" + e);
			return false;
		}
	}

	@Override
	public boolean updateUser(UserInfo userInfo) {
		try {
			sessionFactory.getCurrentSession().update(userInfo);
			return true;
		} catch (Exception e) {
			System.out.println("Exception Occoured" + e);
			return false;
		}
	}

	@Override
	public UserInfo getUser(String loginname) {
		Session session = sessionFactory.openSession();
		UserInfo userInfo = (UserInfo) session.get(UserInfo.class, loginname);
		session.close();
		return userInfo;
	}

	@Override
	public UserInfo checkUser(UserInfo userInfo) {
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from UserInfo where loginname=:myloginname and password=:mypassword");
		query.setParameter("myloginname", userInfo.getLoginname());
		query.setParameter("mypassword",userInfo.getPassword());
		List<UserInfo> listUserInfo = query.list();
		UserInfo userInfo2 = listUserInfo.get(0);
		session.close();
		return userInfo2;
	}

}
