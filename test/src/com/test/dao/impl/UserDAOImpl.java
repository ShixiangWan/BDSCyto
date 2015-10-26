package com.test.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.IUserDAO;
import com.test.entity.User;

@Repository
public class UserDAOImpl implements IUserDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public boolean checkByInfo(String username, String password) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createQuery("from User where username=:username").setParameter("username", username).uniqueResult();
		if(user==null){
			User user2 = new User();
			user2.setUsername(username);
			user2.setPassword(password);
			session.save(user2);
			System.out.println("OK");
			return true;
		}else{
			return true;
		}
	}
	
	
}
