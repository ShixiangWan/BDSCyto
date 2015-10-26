package com.test.dao.impl;

import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.test.dao.ILabDAO;
import com.test.entity.Lab;

@Repository
public class LabDAOImpl implements ILabDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public boolean saveFilename(String filename) {
		Session session = sessionFactory.getCurrentSession();
		try {
			Lab lab = new Lab();
			lab.setFilename(filename);
			session.save(lab);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean checkByFilename(String filename) {
		Session session = sessionFactory.getCurrentSession();
		Lab lab = (Lab) session.createQuery("from Lab where filename=:filename").setParameter("filename", filename).uniqueResult();
		if (lab!=null) {
			return true;
		}
		return false;
	}
	

}
