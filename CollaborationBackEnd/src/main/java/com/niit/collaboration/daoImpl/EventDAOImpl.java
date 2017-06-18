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

import com.niit.collaboration.dao.EventDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.Event;

@Repository("eventDAO")
@Transactional
public class EventDAOImpl implements EventDAO {

	private static Logger log = LoggerFactory.getLogger("EventDAOImpl");

	
	private SessionFactory sessionFactory;

	public EventDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean save(Event event) {
		log.debug("Starting of the Save Method");

		try {
			event.setId(getMaxId() + 1);
			sessionFactory.getCurrentSession().saveOrUpdate(event);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("Starting of the Save Method");
			return false;
		}

	}
	public boolean update(Event event) {
		try {
			sessionFactory.getCurrentSession().update(event);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteById(String id) {

		try {
			sessionFactory.getCurrentSession().delete(getEventById(id));
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

	public List<Event> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from Event").list();
		
		
		
	}

	public Event getEventById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Event where id=?");

		return (Event) query.setString(0, id).uniqueResult();
	}

	public Event getEventByName(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Event where name=?");

		return (Event) query.setString(0, name).uniqueResult();

	}
	
	private int getMaxId() {
		log.debug("->->Starting of the method getMaxId");

		String hql = "select max(id) from Event";
		Query query = sessionFactory.openSession().createQuery(hql);
		Integer maxID;
		try {
			maxID =   (Integer) query.uniqueResult();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		log.debug("Max id :" + maxID);
		return maxID;

	}
	

	
	

	

}
