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

import com.niit.collaboration.dao.ChatForumDAO;
import com.niit.collaboration.model.ChatForum;

@Repository("chatForumDAO")
@Transactional
public class ChatForumDAOImpl implements ChatForumDAO {

	private static Logger log = LoggerFactory.getLogger("ChatForumDAOImpl");

	
	private SessionFactory sessionFactory;

	public ChatForumDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean saveOrupdate(ChatForum chatForum) {
		log.debug("Starting of the Save Method");

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(chatForum);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("Starting of the Save Method");
			return false;
		}

	}

	public boolean deleteById(String id) {

		try {
			sessionFactory.getCurrentSession().delete(getChatForumById(id));
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

	public List<ChatForum> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from ChatForum").list();
		
		
		
	}

	public ChatForum getChatForumById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from ChatForum where id=?");

		return (ChatForum) query.setString(0, id).uniqueResult();
	}

	public ChatForum getChatForumByName(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery("from ChatForum where name=?");

		return (ChatForum) query.setString(0, name).uniqueResult();

	}

	
	

}
