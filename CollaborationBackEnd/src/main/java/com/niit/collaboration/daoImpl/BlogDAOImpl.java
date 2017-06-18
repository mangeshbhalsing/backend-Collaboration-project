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

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	private static Logger log = LoggerFactory.getLogger("BlogDAOImpl");

	
	private SessionFactory sessionFactory;

	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public boolean save(Blog blog) {
		log.debug("Starting of the Save Method");

		try {
			
			
			blog.setId(getMaxId() + 1);
			sessionFactory.getCurrentSession().save(blog);
			
			log.info("name" + blog.getTitle());
			log.info("address" + blog.getUserID());
			log.info("id" + blog.getDescription());
			log.info("id" + blog.getStatus());
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			log.debug("Ending of the Save Method");
			return false;
		}

	}
	
	public boolean update(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteById(String id) {

		try {
			sessionFactory.getCurrentSession().delete(getBlogById(id));
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean deleteByName(String name) {

		try {
			sessionFactory.getCurrentSession().delete(getBlogByName(name));
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}

	}

	public boolean deletebyBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (HibernateException e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public List<Blog> list() {
		
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='Y' ").list();
		
		
		
	}
	
	public List<Blog> listPending() {
		
		return sessionFactory.getCurrentSession().createQuery("from Blog where status='N' ").list();
		
		
		
	}

	public Blog getBlogById(String id) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Blog where id=?");

		return (Blog) query.setString(0, id).uniqueResult();
	}

	public Blog getBlogByName(String name) {
		Query query = sessionFactory.getCurrentSession().createQuery("from Blog where name=?");

		return (Blog) query.setString(0, name).uniqueResult();

	}
	private Integer getMaxId() {
		log.debug("->->Starting of the method getMaxId");

		String hql = "select max(id) from Blog";
		Query query = sessionFactory.openSession().createQuery(hql);
		Integer maxID;
		try {
			maxID = (Integer) query.uniqueResult();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 100;
		}
		log.debug("Max id :" + maxID);
		return maxID;

	}

	public boolean deletebyBlog(String id) {
		try {
			sessionFactory.getCurrentSession().delete((id));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		 
		
	}

	

}
