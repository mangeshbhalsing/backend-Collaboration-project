package com.niit.collaboration.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.collaboration.dao.UserDAO;
import com.niit.collaboration.model.User;

import oracle.net.aso.r;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	private static Logger log = LoggerFactory.getLogger("UserDAOImpl");

	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrupdate(User user) {

		log.debug("Starting of the Save Method");

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			log.info("id" + user.getId());
			log.info("name" + user.getName());
			log.info("address" + user.getPassword());
			log.info("id" + user.getRole());
			log.info("id" + user.getAddress());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("Starting of the Save Method");
			return false;
		}
	}

	/*
	 * public boolean update(User user) {
	 * log.debug("Starting of update method");
	 * sessionFactory.getCurrentSession(). return false; }
	 */

	public List<User> list() {
		log.debug("Starting and Ending of the ****LIST Method****");
		return sessionFactory.getCurrentSession().createQuery("from User where status='N'").list();

	}

	public User get(String id) {
		log.debug("Starting  of the ****get Method of USER module ****");
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		log.debug(" Ending of the ****get Method of USER module ****");
		return user;
	}

	public User isValidate(String id, String password) {
		log.debug("Starting of the **** isValidate Method of USER module ****");

		Query query = sessionFactory.getCurrentSession().createQuery("from User where id=? and password=?");

		query.setString(0, id);

		query.setString(1, password);

		return (User) query.uniqueResult() ;

	}

	public void setOnline(String id) {

		log.debug("Starting of the &&& set online method");

		User user = get(id);
		user.setIsOnline("Y");
		try {
			sessionFactory.getCurrentSession().update(user);
			
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	public void setoffline(String id) {

		log.debug("Starting of the &&& set online method");

		
		try {
			User user = get(id);
			user.setIsOnline("N");
			sessionFactory.getCurrentSession().update(user);
			//return true;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return false;
		
		}
		
	}
	
	@Transactional
	public List<User> notMyFriendList(String userID) {
		String hql = "from User where id not in( SELECT friendId from"
				+ " Friend  where userId ='"+userID + ""
				+ "' OR friendId= '" + userID+ "')";
		log.debug("Query:"+ hql);
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}

}
