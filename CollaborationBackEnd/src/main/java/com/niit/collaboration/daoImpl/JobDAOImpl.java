package com.niit.collaboration.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.JobDAO;
import com.niit.collaboration.model.Job;

@Repository("jobDAO")
@Transactional
public class JobDAOImpl implements JobDAO {

	private static Logger log = LoggerFactory.getLogger("JobDAOImpl");

	
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrupdate(Job job) {
		log.debug("Starting of the Save Method");

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(job);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("Starting of the Save Method");
			return false;
		}

	}

	public boolean deleteById(String id) {

		try {
			sessionFactory.getCurrentSession().delete(getJobById(id));
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}

	}
	
	/*public boolean deleteByName(String name) {

		try {
			sessionFactory.getCurrentSession().delete(getChatByName(name));
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}

	}*/

	/*public boolean deletebyChat(Chat chat) {
		try {
			sessionFactory.getCurrentSession().delete(chat);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}*/

	public List<Job> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from Job").list();
		
		
		
	}

	public Job getJobById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Job where id=?");

		return (Job) query.setString(0, id).uniqueResult();
	}

//	public Job getJobByName(String name) {
//		Query query = sessionFactory.getCurrentSession().createQuery("from Job where name=?");
//
//		return (Job) query.setString(0, name).uniqueResult();
//
//	}

	
	

	

}
