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

import com.niit.collaboration.dao.ChatDAO;
import com.niit.collaboration.model.Chat;

@Repository("chatDAO")
@Transactional
public class ChatDAOImpl implements ChatDAO {

	private static Logger log = LoggerFactory.getLogger("ChatDAOImpl");

	
	private SessionFactory sessionFactory;

	public ChatDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrupdate(Chat chat) {
		log.debug("Starting of the Save Method");

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(chat);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("Starting of the Save Method");
			return false;
		}

	}

	public boolean deleteById(String id) {

		try {
			sessionFactory.getCurrentSession().delete(getChatById(id));
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

	public List<Chat> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from Chat").list();
		
		
		
	}

	public Chat getChatById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Chat where id=?");

		return (Chat) query.setString(0, id).uniqueResult();
	}

	public Chat getChatByName(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Chat where name=?");

		return (Chat) query.setString(0, name).uniqueResult();

	}

	

}
